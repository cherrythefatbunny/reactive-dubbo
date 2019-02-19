package com.github.cherrythefatbunny.demo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.cherrythefatbunny.demo.api.EchoService;
import reactor.core.publisher.Mono;

@Service(proxy = "reactivejavassist")
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
