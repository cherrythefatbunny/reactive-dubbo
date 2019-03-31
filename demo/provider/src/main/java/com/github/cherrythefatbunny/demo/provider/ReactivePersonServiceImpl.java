package com.github.cherrythefatbunny.demo.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cherrythefatbunny.demo.api.EchoService;
import com.github.cherrythefatbunny.demo.api.Person;
import com.github.cherrythefatbunny.demo.api.ReactivePersonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ReactivePersonServiceImpl implements ReactivePersonService, InitializingBean {
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    ReactiveRedisTemplate<String,String> stringReactiveTemplate;
    ReactiveHashOperations<String,String,String> reactiveHashOps;
    @Reference(stub = "com.github.cherrythefatbunny.demo.provider.stub.EchoServiceStub")
    EchoService echoService;

    WebClient.RequestHeadersUriSpec
            requestHeadersUriSpec = WebClient
                                        .builder()
                                        .baseUrl("http://localhost:9091")
                                        .build()
                                        .get();
    @Override
    public Mono<String> getPersonNameById(int id) {
        return reactiveHashOps.get("nickname", id + "")
                .onErrorReturn("")
                .flatMap(this::getPerson)
                .map(Person::getFullName)
                .flatMap(echoService::curseMono);
    }

    @Override
    public Flux<String> getPersonNameList() {
        return getPersonList().map(Person::getFullName).flatMap(echoService::curseMono);
    }
    protected Mono<Person> getPerson(String nickname) {
        return requestHeadersUriSpec
                .uri("/?millis=100&nickname="+nickname)
                .retrieve()
                .bodyToMono(Person.class);
    }
    protected Flux<Person> getPersonList() {
        return requestHeadersUriSpec
                .uri("/list?millis=100")
                .retrieve()
                .bodyToFlux(Person.class);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        reactiveHashOps = stringReactiveTemplate.opsForHash();
    }
}
