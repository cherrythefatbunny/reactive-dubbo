package com.github.cherrythefatbunny.demo.provider;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cherrythefatbunny.demo.api.EchoService;
import com.github.cherrythefatbunny.demo.api.Person;
import com.github.cherrythefatbunny.demo.api.ReactivePersonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.asynchttpclient.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.*;

@Slf4j
@Service
public class ReactivePersonServiceImpl implements ReactivePersonService, InitializingBean {
    String singlePersonUri = "http://localhost:9091/";
    String personListUri = "http://localhost:9091/list";
    AsyncHttpClient client = Dsl.asyncHttpClient();
    BoundRequestBuilder personListbuilder = client.prepareGet(personListUri)
            .addQueryParam("millis","100");
    ExecutorService executorService = Executors.newCachedThreadPool();
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    ReactiveRedisTemplate<String,String> stringReactiveTemplate;
    ReactiveHashOperations<String,String,String> reactiveHashOps;
    @Reference(stub = "com.github.cherrythefatbunny.demo.provider.stub.EchoServiceStub")
    EchoService echoService;
    @Override
    public Mono<String> getPersonNameById(int id) {
        return reactiveHashOps.get("nickname",id+"")
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
        return Mono.create(sink -> {
            ListenableFuture<Response> future =
                    client.prepareGet(singlePersonUri)
                    .addQueryParam("millis","100")
                    .addQueryParam("nickname",nickname).execute();
            future.addListener(() -> {
                String responseStr = null;
                try {
                    responseStr = future.get(3000, TimeUnit.MILLISECONDS).getResponseBody();
                    if(StringUtils.isEmpty(responseStr)) {
                        sink.success();
                    } else {
                        sink.success(objectMapper.readValue(responseStr,Person.class));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    sink.error(e);
                }
            },executorService);
        });
    }
    protected Flux<Person> getPersonList() {
        return Flux.create(sink -> {
            ListenableFuture<Response> future = personListbuilder.execute();
            future.addListener(() -> {
                String responseStr = null;
                try {
                    responseStr = future.get(3000, TimeUnit.MILLISECONDS).getResponseBody();
                    if(!StringUtils.isEmpty(responseStr)) {
                        Person[] personList = objectMapper.readValue(responseStr,Person[].class);
                        Arrays.stream(personList).forEach(sink::next);
                    }
                    sink.complete();
                } catch (Exception e) {
                    e.printStackTrace();
                    sink.error(e);
                }
            },executorService);
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        reactiveHashOps = stringReactiveTemplate.opsForHash();
    }
}
