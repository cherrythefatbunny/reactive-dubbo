package com.github.cherrythefatbunny.demo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.cherrythefatbunny.demo.api.EchoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class EchoController {
    @Reference
    EchoService echoService;
    @GetMapping("echo")
    public Mono<String> echo(@RequestParam String content) {
        return echoService.echoMono(content);
    }
}
