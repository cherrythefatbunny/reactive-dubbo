package com.github.cherrythefatbunny.reactive.dubbo.extensions.proxyfactory;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.AtomicPositiveInteger;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcInvocation;
import com.alibaba.dubbo.rpc.StaticContext;
import com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler;
import com.github.cherrythefatbunny.reactive.dubbo.extensions.reactive.Callback;
import com.github.cherrythefatbunny.reactive.dubbo.extensions.reactive.FluxCallback;
import com.github.cherrythefatbunny.reactive.dubbo.extensions.reactive.MonoCallback;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;

/**
 * reactive implementation of InvokerInvocationHandler
 * @author cherry
 */
public class ReactiveInvokerInvocationHandler extends InvokerInvocationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReactiveInvokerInvocationHandler.class);
    private final Invoker<?> invoker;
    private AtomicPositiveInteger counter;
    public ReactiveInvokerInvocationHandler(Invoker<?> handler) {
        super(handler);
        this.invoker = handler;
        counter = new AtomicPositiveInteger();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //if the invocation returns a publisher,make a publisher wrapping the real invocation
        Class returnType = method.getReturnType();
        if(Publisher.class.isAssignableFrom(returnType)) {
            RpcInvocation invocation = new RpcInvocation(method, args);
            invocation.setAttachment(Constants.ASYNC_KEY,Boolean.TRUE.toString());
            int flowId = counter.getAndIncrement();
            invocation.setAttachment("Publisher",flowId+"");
            if(Mono.class.isAssignableFrom(returnType)) {

                return Mono.create(monoSink -> {
                    MonoCallback monoCallback = new MonoCallback(invocation,monoSink);
                    registerPublisherCallback(monoCallback,invoker,invocation);

                    try {
                        invoker.invoke(invocation).recreate();
                    } catch (Throwable throwable) {
                        if(LOGGER.isErrorEnabled()) {
                            LOGGER.error("mono call invoker error", throwable);
                        }
                        monoSink.error(throwable);
                    }
                });
            } else if(Flux.class.isAssignableFrom(returnType)) {
                return Flux.create(fluxSink -> {
                    FluxCallback fluxCallback = new FluxCallback(invocation,fluxSink);
                    registerPublisherCallback(fluxCallback,invoker,invocation);

                    try {
                        invoker.invoke(invocation).recreate();
                    } catch (Throwable throwable) {
                        if(LOGGER.isErrorEnabled()) {
                            LOGGER.error("flux call invoker error", throwable);
                        }
                        fluxSink.error(throwable);
                    }
                });
            } else {
                //TODO other publishers support
                throw new IllegalArgumentException(
                        String.format("%s not supported now",method.getReturnType().getSimpleName()));
            }
        }
        return super.invoke(proxy, method, args);
    }
    private void registerPublisherCallback(Callback callback, Invoker invoker,RpcInvocation invocation) {
        //register `onreturn` method
        String flowId = invocation.getAttachment("Publisher");
        if(StringUtils.isBlank(flowId)) {
            if(LOGGER.isErrorEnabled()) {
                LOGGER.error("flowId should be given");
            }
            return;
        }
        String onreturnMethodKey = StaticContext.getKey(invoker.getUrl(), invocation.getMethodName(), Constants.ON_RETURN_METHOD_KEY);
        try {
            StaticContext.getContext("Publisher"+flowId).put(onreturnMethodKey, callback.getClass().getDeclaredMethod("onreturn",Object.class));
        } catch (NoSuchMethodException e) {
            LOGGER.error(e);
        }
        String onreturnInstKey = StaticContext.getKey(invoker.getUrl(), invocation.getMethodName(), Constants.ON_RETURN_INSTANCE_KEY);
        StaticContext.getContext("Publisher"+flowId).put(onreturnInstKey, callback);
        //register `onthrow` method
        String onThrowMethodKey = StaticContext.getKey(invoker.getUrl(), invocation.getMethodName(), Constants.ON_THROW_METHOD_KEY);
        try {
            StaticContext.getContext("Publisher"+flowId).put(onThrowMethodKey,callback.getClass().getDeclaredMethod("onthrow",Throwable.class));
        } catch (NoSuchMethodException e) {
            LOGGER.error(e);
        }
        String onThrowInstKey = StaticContext.getKey(invoker.getUrl(), invocation.getMethodName(), Constants.ON_THROW_INSTANCE_KEY);
        StaticContext.getContext("Publisher"+flowId).put(onThrowInstKey, callback);
        if(callback instanceof FluxCallback) {
            invocation.setAttachment("PublisherType","flux");
        } else if(callback instanceof MonoCallback) {
            invocation.setAttachment("PublisherType","mono");
        }
    }
}
