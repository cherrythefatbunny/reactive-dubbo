package com.github.cherrythefatbunny.demo.api;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactivePersonService {
    Mono<String> getPersonNameById(int id);
    Flux<String> getPersonNameList();
}
