package com.github.cherrythefatbunny.reactive.dubbo.extensions.proxyfactory;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcInvocation;
import com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.util.List;

/**
 * reactive implementation of InvokerInvocationHandler
 * @author cherry
 */
public class ReactiveInvokerInvocationHandler extends InvokerInvocationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReactiveInvokerInvocationHandler.class);
    private final Invoker<?> invoker;
    public ReactiveInvokerInvocationHandler(Invoker<?> handler) {
        super(handler);
        this.invoker = handler;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //if the invocation returns a publisher,make a publisher wrapping the real invocation
        Class returnType = method.getReturnType();
        if(Publisher.class.isAssignableFrom(returnType)) {
            RpcInvocation invocation = new RpcInvocation(method, args);
            if(Mono.class.isAssignableFrom(returnType)) {
                invocation.setAttachment("Publisher","mono");
                return Mono.fromCallable(() -> {
                    try {
                        return invoker.invoke(invocation).recreate();
                    } catch (Throwable throwable) {
                        if(LOGGER.isWarnEnabled()) {
                            LOGGER.warn("mono call invoker error", throwable);
                        }
                        throw new Exception(throwable);
                    }
                });
            } else if(Flux.class.isAssignableFrom(returnType)) {
                invocation.setAttachment("Publisher","flux");
                return Flux.fromIterable(Mono.fromCallable(() -> {
                    try {
                        return (List)invoker.invoke(invocation).recreate();
                    } catch (Throwable throwable) {
                        if(LOGGER.isWarnEnabled()) {
                            LOGGER.warn("flux call invoker error", throwable);
                        }
                        throw new Exception(throwable);
                    }
                }).block());
            } else {
                //TODO other publishers support
                throw new IllegalArgumentException(
                        String.format("%s not supported now",method.getReturnType().getSimpleName()));
            }
        }
        return super.invoke(proxy, method, args);
    }

}
