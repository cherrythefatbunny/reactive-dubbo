package com.github.cherrythefatbunny.reactive.dubbo.extensions.reactive;

/**
 * Reactive callback adapter to be called when dubbo async invocation returns
 * @author cherrythefatbunny
 */
public interface Callback {
    void onreturn(Object result);
    void onthrow(Throwable throwable);
}
