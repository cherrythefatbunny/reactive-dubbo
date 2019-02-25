package com.github.cherrythefatbunny.demo.consumer.stub;

import com.github.cherrythefatbunny.demo.api.EchoService;
import reactor.core.publisher.Mono;

public class EchoServiceStub implements EchoService {
    private EchoService echoService;

    public EchoServiceStub(EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public String echo(String content) {
        String word = echoService.echo(content);
        return exclamatory(word);
    }

    @Override
    public Mono<String> echoMono(String content) {
        return echoService.echoMono(content).map(EchoServiceStub::exclamatory);
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
}
