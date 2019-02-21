package com.github.cherrythefatbunny.reactive.dubbo.extensions.proxyfactory;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcInvocation;
import com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler;
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
        if(method.getReturnType().equals(Mono.class)||method.getReturnType().equals(Flux.class)) {
            RpcInvocation invocation = new RpcInvocation(method, args);
            if(method.getReturnType().equals(Mono.class)) {
                invocation.setAttachment("Publisher","mono");
                return Mono.fromCallable(() -> {
                    try {
                        return invoker.invoke(invocation).recreate();
                    } catch (Throwable throwable) {
                        LOGGER.error("mono call invoker error:"+throwable);
                        return null;
                    }
                });
            } else if(method.getReturnType().equals(Flux.class)) {
                invocation.setAttachment("Publisher","flux");
                return Flux.fromIterable(Mono.fromCallable(() -> {
                    try {
                        return (List)invoker.invoke(invocation).recreate();
                    } catch (Throwable throwable) {
                        LOGGER.error("flux call invoker error:"+throwable);
                        return null;
                    }
                }).block());
            }
        }
        return super.invoke(proxy, method, args);
    }

}
