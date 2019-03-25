package com.github.cherrythefatbunny.reactive.dubbo.extensions.proxyfactory;

import org.apache.dubbo.rpc.Invoker;

import java.lang.reflect.Proxy;

/**
 * reactive implementation of JdkProxyFactory
 * @author cherry
 */
public class ReactiveJdkProxyFactory extends AbstractReactiveProxyFactory {
    public static final String NAME = "reactivejdk";

    @Override
    public <T> T getProxy(Invoker<T> invoker, Class<?>[] interfaces) {
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                interfaces, new ReactiveInvokerInvocationHandler(invoker));
    }
}
