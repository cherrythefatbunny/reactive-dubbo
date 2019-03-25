package com.github.cherrythefatbunny.demo.provider.stub;

import com.github.cherrythefatbunny.demo.api.EchoService;
import reactor.core.publisher.Mono;

public class EchoServiceStub implements EchoService {
    private EchoService echoService;

    public EchoServiceStub(EchoService echoService) {
        this.echoService = echoService;
    }

    /**
     * filter key word
     * */
    public static String filter(String word) {
        if(word.contains("fuck")) {
            return word.replace("fuck","f**k");
        } else {
            return word;
        }
    }

    /**
     * exclamatory form
     * */
    public static String exclamatory(String word) {
        if (!word.endsWith("!!!")) {
            return word+"!!!";
        } else {
            return word;
        }
    }

    @Override
    public String echo(String content) {
        return echoService.echo(content);
    }

    @Override
    public Mono<String> echoMono(String content) {
        return echoService.echoMono(content);
    }

    @Override
    public String curse(String name) {
        return exclamatory(filter(echoService.curse(name)));
    }

    @Override
    public Mono<String> curseMono(String name) {
        return echoService
                .curseMono(name)
                .map(EchoServiceStub::filter)
                .map(EchoServiceStub::exclamatory);
    }
}
