package com.github.cherrythefatbunny.demo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.cherrythefatbunny.demo.api.Person;
import com.github.cherrythefatbunny.demo.api.ReactivePersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

//given specified ProxyFactory to deal with mono and flux
@Slf4j
@Service(proxy = "reactivejavassist",timeout = 100000)
public class ReactivePersonServiceImpl implements ReactivePersonService, InitializingBean {
    @Autowired
    @Qualifier("jsonReactiveTemplate")
    ReactiveRedisTemplate<String,Person> reactiveOps;
    ReactiveHashOperations<String,String,Person> reactiveHashOps;
    @Override
    public Mono<String> getPersonNameById(int id) {
        return reactiveHashOps.get("person",id+"").onErrorReturn(null).map(Person::getName);
    }

    @Override
    public Flux<String> getPersonNameList() {
        return reactiveHashOps.values("person").map(Person::getName);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        reactiveHashOps = reactiveOps.opsForHash();
    }
}
