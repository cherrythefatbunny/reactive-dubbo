package com.github.cherrythefatbunny.demo.provider;

import com.github.cherrythefatbunny.demo.api.EchoService;
import org.apache.dubbo.config.annotation.Service;
import reactor.core.publisher.Mono;

@Service
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String content) {
        return content;
    }

    @Override
    public Mono<String> echoMono(String content) {
        return Mono.just(content);
    }

    @Override
    public String curse(String name) {
        return "fuck " + name;
    }

    @Override
    public Mono<String> curseMono(String name) {
        return Mono.just("fuck " + name);
    }
}
