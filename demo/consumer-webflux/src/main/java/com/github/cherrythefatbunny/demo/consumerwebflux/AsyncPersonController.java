package com.github.cherrythefatbunny.demo.consumerwebflux;

import com.github.cherrythefatbunny.demo.api.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RequestMapping("async")
@RestController
public class AsyncPersonController {
    @Reference
    PersonService personService;
    ExecutorService executorService = Executors.newCachedThreadPool();
    @GetMapping("by")
    public Mono<String> getPersonNameById(@RequestParam int id) {
        return Mono.create(sink -> {
            executorService.execute(() -> {
                try {
                    sink.success(personService.getPersonNameById(id));
                } catch (Exception ex) {
                    sink.error(ex);
                }
            });
        });
    }
    @GetMapping("list")
    public Flux<String> getPersonNameList() {
        return Flux.create(sink -> {
            executorService.execute(() -> {
                try {
                    List<String> nameList = personService.getPersonNameList();
                    if(nameList!=null&&!nameList.isEmpty()) {
                        nameList.forEach(sink::next);
                    }
                    sink.complete();
                } catch (Exception ex) {
                    sink.error(ex);
                }
            });
        });
    }
}
