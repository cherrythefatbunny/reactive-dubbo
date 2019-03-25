package com.github.cherrythefatbunny.demo.consumerwebflux;

import com.github.cherrythefatbunny.demo.api.ReactivePersonService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactivePersonController {
    @Reference
    ReactivePersonService reactivePersonService;
    @GetMapping("by")
    public Mono<String> getPersonNameById(@RequestParam int id) {
        return reactivePersonService.getPersonNameById(id);
    }
    @GetMapping("list")
    public Flux<String> getPersonNameList() {
        return reactivePersonService.getPersonNameList();
    }
}
