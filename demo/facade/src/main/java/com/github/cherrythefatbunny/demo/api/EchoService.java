package com.github.cherrythefatbunny.demo.api;

import reactor.core.publisher.Mono;

public interface EchoService {
    String echo(String content);
    Mono<String> echoMono(String content);
}
