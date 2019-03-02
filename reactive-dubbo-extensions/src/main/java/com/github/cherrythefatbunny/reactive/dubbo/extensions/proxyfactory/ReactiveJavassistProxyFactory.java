package com.github.cherrythefatbunny.reactive.dubbo.extensions.proxyfactory;

import com.alibaba.dubbo.common.bytecode.Proxy;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.proxy.javassist.JavassistProxyFactory;

/**
 * reactive implementation of JavassistProxyFactory
 * @author cherry
 */
public class ReactiveJavassistProxyFactory extends JavassistProxyFactory {
    public static final String NAME = "reactivejavassist";

    @Override
    public <T> T getProxy(Invoker<T> invoker, Class<?>[] interfaces) {
        return (T) Proxy.getProxy(interfaces).newInstance(new ReactiveInvokerInvocationHandler(invoker));
    }
}
