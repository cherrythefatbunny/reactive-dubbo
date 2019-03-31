![logo](logo.png)
--
![maven](https://img.shields.io/maven-central/v/com.github.cherrythefatbunny/reactive-dubbo.svg)
![license](https://img.shields.io/github/license/cherrythefatbunny/reactive-dubbo.svg)

Reactive support for [Dubbo](http://dubbo.apache.org) based on [REACTOR](https://projectreactor.io/)

## Getting started
### Install
```bash
# git clone https://github.com/cherrythefatbunny/reactive-dubbo.git
# cd reactive-dubbo
# mvn clean install
```
### Run redis and zookeeper
```bash
# cd demo/redis-and-zookeeper
# nohup mvn spring-boot:run &
```
### Run Demo Provider
```bash
# cd ../provider
# nohup mvn spring-boot:run &
```
### Run Demo Consumer
```bash
# cd ../consumer
# nohup mvn spring-boot:run &
```

## Getting involved

### Maven dependency
Both provider and consumer should add reactive-dubbo-starter dependency
```xml
<dependency>
    <groupId>com.github.cherrythefatbunny</groupId>
    <artifactId>reactive-dubbo-starter</artifactId>
    <version>LATESTVERSION</version>
</dependency>
```
### Service definition
As provider side,you can define reactive services without specifying a reactive proxy factory(the default proxy factory has been changed to `reactivejavassist` by [ReactiveDefaultPropertiesEnvironmentPostProcessor.java](https://github.com/cherrythefatbunny/reactive-dubbo/blob/master/reactive-dubbo-starter/src/main/java/com/github/cherrythefatbunny/reactive/dubbo/boot/ReactiveDefaultPropertiesEnvironmentPostProcessor.java) )
```java
@Service
public class ReactiveServiceImpl implements ReactiveService {
}
```

## Architecture
### Dubbo with Reactive Dubbo
To make dubbo reactive,Reactive Dubbo will replace invokers and proxies with the reactive ones by specifying a reactive proxy factory
![architecture](architecture.jpg)

### Reactive proxy
A reactive proxy (the reactiveInvokerInvocationHandler actually works) wraps a formal functional invocation into a reactive publisher

[ReactiveInvokerInvocationHandler.java](https://github.com/cherrythefatbunny/reactive-dubbo/blob/master/reactive-dubbo-extensions/src/main/java/com/github/cherrythefatbunny/reactive/dubbo/extensions/proxyfactory/ReactiveInvokerInvocationHandler.java):
```java
public class ReactiveInvokerInvocationHandler extends InvokerInvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //if the invocation returns a publisher,make a publisher wrapping the real invocation
        Class returnType = method.getReturnType();
        if (Publisher.class.isAssignableFrom(returnType)) {
            RpcInvocation invocation = createInvocation(method, args);
            if (Mono.class.isAssignableFrom(returnType)) {
                invocation.setAttachment(KEY_PUBLISHER_TYPE,VALUE_PUBLISHER_MONO);
                return Mono.create(monoSink -> {
                    try {
                        CompletableFuture<Object> future
                                = (CompletableFuture<Object>) invoker.invoke(invocation).recreate();
                        future.whenComplete((v, t) -> {
                            if (t != null) {
                                monoSink.error(t);
                            } else {
                                monoSink.success(v);
                            }
                        });
                    } catch (Throwable throwable) {
                        if(LOGGER.isErrorEnabled()) {
                            LOGGER.error("mono invocation",throwable);
                        }
                        monoSink.error(throwable);
                    }
                });
            } else if (Flux.class.isAssignableFrom(returnType)) {
                invocation.setAttachment(KEY_PUBLISHER_TYPE,VALUE_PUBLISHER_FLUX);
                return Flux.create(fluxSink -> {
                    try {
                        CompletableFuture<Object> future
                                = (CompletableFuture<Object>) invoker.invoke(invocation).recreate();
                        future.whenComplete((v, t) -> {
                            if (t != null) {
                                fluxSink.error(t);
                            } else if (v instanceof List){
                                List list = (List) v;
                                if(list!=null) {
                                    list.forEach(fluxSink::next);
                                }
                                fluxSink.complete();
                            } else {
                                Exception ex = new IllegalArgumentException("unexpected return type:"+v.getClass());
                                fluxSink.error(ex);
                            }
                        });
                    } catch (Throwable throwable) {
                        if(LOGGER.isErrorEnabled()) {
                            LOGGER.error("flux invocation",throwable);
                        }
                        fluxSink.error(throwable);
                    }
                });
            } else {
                //TODO other publishers support
                throw new IllegalArgumentException(
                        String.format("%s not supported now", method.getReturnType().getSimpleName()));
            }
        }
        return super.invoke(proxy, method, args);
    }
}
```

### Reactive invoker
A reactive invoker intercepts a formal remote request and convert its result into publisher's parameterized type(e.g. `Mono<String>` -> `String`,`Flux<String>` -> `List<String>`).The invoker combines reactive provider apis with the CompletableFuture result which is greatly improved since Dubbo 2.7.0 
[AbstractReactiveProxyInvoker.java](https://github.com/cherrythefatbunny/reactive-dubbo/blob/master/reactive-dubbo-extensions/src/main/java/com/github/cherrythefatbunny/reactive/dubbo/extensions/proxyfactory/AbstractReactiveProxyInvoker.java):
```java
public abstract class AbstractReactiveProxyInvoker<T> extends AbstractProxyInvoker<T> {
    @Override
    public Result invoke(Invocation invocation) throws RpcException {
        String publisher = invocation.getAttachment(KEY_PUBLISHER_TYPE);
        if(StringUtils.isBlank(publisher)) {
            return super.invoke(invocation);
        }
        RpcContext rpcContext = RpcContext.getContext();
        try {
            Object obj = doInvoke(proxy, invocation.getMethodName(), invocation.getParameterTypes(), invocation.getArguments());
            Mono mono = null;
            if(publisher.equals(VALUE_PUBLISHER_MONO)) {
                mono = (Mono) obj;
            } else if(publisher.equals(VALUE_PUBLISHER_FLUX)) {
                Flux<Object> flux = (Flux<Object>) obj;
                mono = flux.collect(ArrayList::new,ArrayList::add);
            }
            if(mono==null) {
                CompletableFuture future = new CompletableFuture();
                Exception ex = new IllegalArgumentException("unexpected publisher type:"+publisher);
                future.completeExceptionally(ex);
                return new AsyncRpcResult(future);
            } else {
                return new AsyncRpcResult(mono.toFuture());
            }
        } catch (InvocationTargetException e) {
            // TODO async throw exception before async thread write back, should stop asyncContext
            if (rpcContext.isAsyncStarted() && !rpcContext.stopAsync()) {
                LOGGER.error("Provider async started, but got an exception from the original method, cannot write the exception back to consumer because an async result may have returned the new thread.", e);
            }
            return new RpcResult(e.getTargetException());
        } catch (Throwable e) {
            throw new RpcException("Failed to invoke remote proxy method " + invocation.getMethodName() + " to " + getUrl() + ", cause: " + e.getMessage(), e);
        }
    }
}
```

### RpcUtilsCracker
Since reactive responses will be the publisher's parameterized type which can cause `HessianProtocolException`
,the consumer side should convert the decode types.Here is my solution: crack the `RpcUtils.getReturnTypes` method with javassist.

[RpcUtilsCracker.java](https://github.com/cherrythefatbunny/reactive-dubbo/blob/master/reactive-dubbo-extensions/src/main/java/com/github/cherrythefatbunny/reactive/dubbo/extensions/rpc/support/RpcUtilsCracker.java):
```java
public class RpcUtilsCracker {
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcUtilsCracker.class);
    private static final String RPCUTILS_CLASS_NAME = "com.alibaba.dubbo.rpc.support.RpcUtils";
    public static void crack() {
        try {
            ClassPool classPool = ClassPool.getDefault();
            classPool.appendClassPath(new LoaderClassPath(RpcUtilsCracker.class.getClassLoader()));
            CtClass ctClass = classPool.get(RPCUTILS_CLASS_NAME);
            CtMethod ctMethod = ctClass.getDeclaredMethod("getReturnTypes");
            //rename method `getReturnTypes` to `getReturnTypes0`
            ctClass.removeMethod(ctMethod);
            ctMethod.setName("getReturnTypes0");
            ctClass.addMethod(ctMethod);
            //add new method `getReturnTypes` according to RpcUtilsCracker.getReturnTypes
            CtClass ctClass1 = classPool.get(RpcUtilsCracker.class.getName());
            ctMethod = new CtMethod(ctClass1.getDeclaredMethod("getReturnTypes"),ctClass,null);
            ctClass.addMethod(ctMethod);
            ctClass.toClass();
        } catch (NotFoundException|CannotCompileException e) {
            LOGGER.error("crack RpcUtils failed",e);
        }
    }
    public static Type[] getReturnTypes0(Invocation invocation) {
        return null;
    }

    /**
     * template method of RpcUtils.getReturnTypes
     * @param invocation the invocation instance of the interface
     * @return return types of the invokee
     * */
    public static Type[] getReturnTypes(Invocation invocation) {
        Type[] types = getReturnTypes0(invocation);
        if(types==null)return null;
        if (types[0] == Mono.class) {
            Class<Mono> returnType = (Class<Mono>) types[0];
            ParameterizedType genericReturnType = (ParameterizedType) types[1];
            return new Type[]{genericReturnType.getActualTypeArguments()[0]};
        }
        if (types[0] == Flux.class) {
            Class<Flux> returnType = (Class<Flux>) types[0];
            ParameterizedType genericReturnType = (ParameterizedType) types[1];
            genericReturnType = ParameterizedTypeImpl.make(List.class,new Type[]{genericReturnType.getActualTypeArguments()[0]},null);
            return new Type[]{List.class, genericReturnType};
        }
        return types;
    }
}
```