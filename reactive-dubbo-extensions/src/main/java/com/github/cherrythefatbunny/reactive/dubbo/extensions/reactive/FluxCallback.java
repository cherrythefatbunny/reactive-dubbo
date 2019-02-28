package com.github.cherrythefatbunny.reactive.dubbo.extensions.reactive;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.rpc.Invocation;
import reactor.core.publisher.FluxSink;

import java.util.List;

public class FluxCallback implements Callback {
    private static Logger LOGGER = LoggerFactory.getLogger(FluxCallback.class);

    protected FluxSink fluxSink;
    protected Invocation invocation;
    public FluxCallback(Invocation invocation, FluxSink fluxSink) {
        this.fluxSink = fluxSink;
        this.invocation = invocation;
    }
    @Override
    public void onreturn(Object result) {
        if(result instanceof List) {
            List list = (List) result;
            list.forEach(fluxSink::next);
            fluxSink.complete();
        } else {
            fluxSink.error(new IllegalArgumentException(String.format("unsupposed return type:%s",result.getClass().getSimpleName())));
        }
    }
    @Override
    public void onthrow(Throwable throwable) {
        if(LOGGER.isErrorEnabled()) {
            LOGGER.error(String.format("invocation error[method:%s]",invocation.getMethodName()),throwable);
        }
        fluxSink.error(throwable);
    }
}
