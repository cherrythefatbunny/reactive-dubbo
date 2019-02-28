package com.github.cherrythefatbunny.reactive.dubbo.extensions.reactive;

public interface Callback {
    void onreturn(Object result);
    void onthrow(Throwable throwable);
}
