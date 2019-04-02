package com.github.cherrythefatbunny.demo.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cherrythefatbunny.demo.api.EchoService;
import com.github.cherrythefatbunny.demo.api.FuturePersonService;
import com.github.cherrythefatbunny.demo.api.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.Dsl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class FuturePersonServiceImpl implements FuturePersonService, InitializingBean {
    String singlePersonUri = "http://localhost:9091/";
    String personListUri = "http://localhost:9091/list";
    AsyncHttpClient client = Dsl.asyncHttpClient();
    BoundRequestBuilder personListbuilder = client.prepareGet(personListUri)
            .addQueryParam("millis","100");

    @Autowired
    RedisTemplate<String, String> stringTemplate;
    @Autowired
    ReactiveRedisTemplate<String, String> stringReactiveTemplate;
    HashOperations<String,String,String> hashOps;
    ReactiveHashOperations<String, String, String> reactiveHashOps;

    @Reference(stub = "com.github.cherrythefatbunny.demo.provider.stub.EchoServiceStub")
    EchoService echoService;
    ObjectMapper objectMapper = new ObjectMapper();

    WebClient.RequestHeadersUriSpec
            requestHeadersUriSpec = WebClient
                                        .builder()
                                        .baseUrl("http://localhost:9091")
                                        .build()
                                        .get();

    @Override
    public CompletableFuture<List<String>> getPersonNameList() {
        return getPersonList()
                .map(Person::getFullName)
                .flatMap(echoService::curseMono)
                .collectList()
                .toFuture();
    }

    @Override
    public CompletableFuture<String> getPersonNameById(int id) {
        return reactiveHashOps.get("nickname", id + "")
                .onErrorReturn("")
                .flatMap(this::getPerson)
                .map(Person::getFullName)
                .flatMap(echoService::curseMono)
                .toFuture();
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
        hashOps = stringTemplate.opsForHash();
        reactiveHashOps = stringReactiveTemplate.opsForHash();
    }
}
