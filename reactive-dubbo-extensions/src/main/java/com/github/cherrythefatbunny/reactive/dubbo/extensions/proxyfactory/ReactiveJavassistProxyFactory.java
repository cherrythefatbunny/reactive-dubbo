package com.github.cherrythefatbunny.reactive.dubbo.extensions.proxyfactory;

import org.apache.dubbo.common.bytecode.Proxy;
import org.apache.dubbo.rpc.Invoker;

/**
 * reactive implementation of JavassistProxyFactory
 * @author cherry
 */
public class ReactiveJavassistProxyFactory extends AbstractReactiveProxyFactory {
    public static final String NAME = "reactivejavassist";

    @Override
    public <T> T getProxy(Invoker<T> invoker, Class<?>[] interfaces) {
        return (T) Proxy.getProxy(interfaces).newInstance(new ReactiveInvokerInvocationHandler(invoker));
    }

}
