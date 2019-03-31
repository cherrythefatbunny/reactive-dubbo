package com.github.cherrythefatbunny.demo.delayservice;

import com.github.cherrythefatbunny.demo.api.Person;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;

@RestController
public class DelayController implements InitializingBean {
    @Autowired
    ReactiveRedisOperations<String, Person> jsonReactiveTemplate;
    @Autowired
    ReactiveRedisOperations<String, String> stringReactiveTemplate;
    ReactiveHashOperations<String, String, Person> hashOperations;
    @GetMapping
    public Mono<Person> getPerson(@RequestParam String nickname, @RequestParam int millis) {
        return hashOperations.get("person", nickname)
                .delayElement(Duration.ofMillis(millis));
    }
    @GetMapping("list")
    public Flux<Person> getPersonList(@RequestParam int millis) {
        return hashOperations.values("person")
                .collect(ArrayList<Person>::new,ArrayList<Person>::add)
                .delayElement(Duration.ofMillis(millis))
                .flatMapMany(Flux::fromIterable);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        hashOperations = jsonReactiveTemplate.opsForHash();
    }
}
