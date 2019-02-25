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

    @Override
    public String echo(String content) {
        return filter(echoService.echo(content));
    }

    @Override
    public Mono<String> echoMono(String content) {
        return echoService.echoMono(content).map(EchoServiceStub::filter);
    }
}
