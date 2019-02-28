package com.github.cherrythefatbunny.reactive.dubbo.extensions.reactive;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.rpc.Invocation;
import reactor.core.publisher.MonoSink;

import java.util.Arrays;

public class MonoCallback implements Callback {
    private static Logger LOGGER = LoggerFactory.getLogger(MonoCallback.class);
    protected MonoSink monoSink;
    protected Invocation invocation;

    public MonoCallback(Invocation invocation, MonoSink monoSink) {
        this.monoSink = monoSink;
        this.invocation = invocation;
    }
    @Override
    public void onreturn(Object result) {
        monoSink.success(result);
    }
    @Override
    public void onthrow(Throwable throwable) {
        if(LOGGER.isErrorEnabled()) {
            LOGGER.error(String.format("invocation error[method:%s]",invocation.getMethodName()),throwable);
        }
        monoSink.error(throwable);
    }
}
