package com.github.cherrythefatbunny.demo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.cherrythefatbunny.demo.api.EchoService;
import com.github.cherrythefatbunny.demo.consumer.stub.EchoServiceStub;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class EchoController {
    @Reference(stub = "com.github.cherrythefatbunny.demo.consumer.stub.EchoServiceStub")
    EchoService echoService;
    @GetMapping("echo")
    public Mono<String> echo(@RequestParam String content) {
        return echoService.echoMono(content);
    }
}
