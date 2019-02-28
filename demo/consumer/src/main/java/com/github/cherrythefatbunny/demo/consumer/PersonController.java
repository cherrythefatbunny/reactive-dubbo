package com.github.cherrythefatbunny.demo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.cherrythefatbunny.demo.api.PersonService;
import com.github.cherrythefatbunny.demo.api.ReactivePersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class PersonController {
    @Reference
    PersonService personService;
    @Reference
    ReactivePersonService reactivePersonService;
    @GetMapping("list")
    public List<String> getPersonNameList() {
        return personService.getPersonNameList();
    }
    @GetMapping("by")
    public String getPersonNameById(@RequestParam int id) {
        return personService.getPersonNameById(id);
    }
    @GetMapping("by1")
    public Mono<String> getPersonNameById1(@RequestParam int id) {
        return reactivePersonService.getPersonNameById(id);
    }
    @GetMapping("list1")
    public Flux<String> getPersonNameList1() {
        return reactivePersonService.getPersonNameList();
    }
}
