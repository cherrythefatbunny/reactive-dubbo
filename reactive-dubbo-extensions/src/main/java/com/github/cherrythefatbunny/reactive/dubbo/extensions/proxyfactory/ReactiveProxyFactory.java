package com.github.cherrythefatbunny.reactive.dubbo.extensions.proxyfactory;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.proxy.AbstractProxyFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

import static com.alibaba.dubbo.common.Constants.*;

/**
 * @author cherrythefatbunny
 */
public abstract class ReactiveProxyFactory extends AbstractProxyFactory {
    protected ProxyFactory delegating = ExtensionLoader
            .getExtensionLoader(ProxyFactory.class).getExtension(getExtensionName());

    /**
     * return the name of delegating ProxyFactory
     * */
    protected abstract String getExtensionName();

    @Override
    public <T> Invoker<T> getInvoker(T proxy, Class<T> type, URL url) throws RpcException {
        Invoker<T> invoker = delegating.getInvoker(proxy, type, url);
        Invoker<T> wrapper = (Invoker<T>) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{Invoker.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object ret = method.invoke(invoker, args);
                //extra process dealing with remote requests
                if(method.getName().equals("invoke")&&
                        !LOCAL_PROTOCOL.equals(((RpcInvocation) args[0]).getInvoker().getUrl().getProtocol())) {
                    RpcResult rpcResult = (RpcResult) ret;
                    Object val = rpcResult.getValue();
                    //retrieve real returned value and create new RpcResult
                    if(val instanceof Mono) {
                        Mono mono = (Mono) val;
                        return new RpcResult(mono.block());
                    }
                    //retrieve real returned value,collect with an ArrayList and create new RpcResult
                    if(val instanceof Flux) {
                        Flux<Object> flux = (Flux) val;
                        return new RpcResult(flux.collect(ArrayList::new, ArrayList::add).block());
                    }
                }
                return ret;
            }
        });
        return wrapper;
    }
}
