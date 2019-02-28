package com.github.cherrythefatbunny.demo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.cherrythefatbunny.demo.api.EchoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class EchoController {
    @Reference(stub = "com.github.cherrythefatbunny.demo.consumer.stub.EchoServiceStub")
    EchoService echoService;
    WebClient webClient = WebClient.builder().baseUrl("http://www.baidu.com").build();
    @GetMapping("echo")
    public Mono<String> echo(@RequestParam String content) {
        return echoService.echoMono(content);
    }
    @GetMapping("test")
    public Mono<String> test() {
        return webClient.get().uri("/").exchange()
                .flatMap(response -> response.bodyToMono(String.class));
    }
}
