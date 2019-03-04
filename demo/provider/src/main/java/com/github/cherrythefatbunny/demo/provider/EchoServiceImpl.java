package com.github.cherrythefatbunny.demo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.cherrythefatbunny.demo.api.EchoService;
import com.github.cherrythefatbunny.reactive.dubbo.extensions.proxyfactory.ReactiveJavassistProxyFactory;
import reactor.core.publisher.Mono;

@Service(proxy = ReactiveJavassistProxyFactory.NAME)
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String content) {
        return content;
    }

    @Override
    public Mono<String> echoMono(String content) {
        return Mono.just(content);
    }
}
