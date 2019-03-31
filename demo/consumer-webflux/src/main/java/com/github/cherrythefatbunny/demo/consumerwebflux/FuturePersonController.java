package com.github.cherrythefatbunny.demo.consumerwebflux;

import com.github.cherrythefatbunny.demo.api.FuturePersonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequestMapping("future")
@RestController
public class FuturePersonController {
    @Reference
    FuturePersonService futurePersonService;
    @GetMapping("by")
    public Mono<String> getPersonNameById(@RequestParam int id) {
        return Mono.create(sink -> {
           CompletableFuture<String> future = futurePersonService.getPersonNameById(id);
           future.whenComplete((v,t) -> {
               if(t!=null) {
                   sink.error(t);
               } else {
                   sink.success(v);
               }
           });
        });
    }
    @GetMapping("list")
    public Flux<String> getPersonNameList() {
        return Flux.create(sink -> {
            CompletableFuture<List<String>> future = futurePersonService.getPersonNameList();
            future.whenComplete((v,t) -> {
                if(t!=null) {
                    sink.error(t);
                } else {
                    List<String> list = v;
                    if(list!=null) {
                        list.forEach(sink::next);
                    }
                    sink.complete();
                }
            });
        });
    }
}
