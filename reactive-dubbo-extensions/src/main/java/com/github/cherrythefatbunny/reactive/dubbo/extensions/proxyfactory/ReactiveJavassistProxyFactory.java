package com.github.cherrythefatbunny.reactive.dubbo.extensions.proxyfactory;

import com.alibaba.dubbo.common.bytecode.Proxy;
import com.alibaba.dubbo.rpc.Invoker;

/**
 * reactive implementation of JavassistProxyFactory
 * @author cherry
 */
public class ReactiveJavassistProxyFactory extends ReactiveProxyFactory {

    @Override
    protected String getExtensionName() {
        return "javassist";
    }

    @Override
    public <T> T getProxy(Invoker<T> invoker, Class<?>[] interfaces) {
        return (T) Proxy.getProxy(interfaces).newInstance(new ReactiveInvokerInvocationHandler(invoker));
    }
}
