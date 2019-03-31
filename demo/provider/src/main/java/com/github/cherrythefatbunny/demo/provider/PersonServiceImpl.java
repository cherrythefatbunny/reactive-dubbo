package com.github.cherrythefatbunny.demo.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cherrythefatbunny.demo.api.EchoService;
import com.github.cherrythefatbunny.demo.api.Person;
import com.github.cherrythefatbunny.demo.api.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.Dsl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService, InitializingBean {
    String singlePersonUri = "http://localhost:9091/";
    String personListUri = "http://localhost:9091/list";
    AsyncHttpClient client = Dsl.asyncHttpClient();
    BoundRequestBuilder personListbuilder = client.prepareGet(personListUri)
            .addQueryParam("millis","100");

    @Autowired
    RedisTemplate<String, String> stringTemplate;
    @Reference(stub = "com.github.cherrythefatbunny.demo.provider.stub.EchoServiceStub")
    EchoService echoService;
    HashOperations<String,String,String> hashOps;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<String> getPersonNameList() {
        Person[] personList = getPersonList();
        return personList==null?null:
                Arrays.asList(personList).stream()
                        .map(Person::getFullName)
                        .map(echoService::curse)
                        .collect(Collectors.toList());
    }

    @Override
    public String getPersonNameById(int id) {
        String nickname = hashOps.get("nickname",id+"");
        if(nickname==null)return null;
        Person person = getPerson(nickname);
        return person==null?null
                :echoService.curse(person.getFullName());
    }
    protected Person getPerson(String nickname) {
        String responseStr = null;
        try {
            responseStr = client.prepareGet(singlePersonUri)
                    .addQueryParam("millis","100")
                    .addQueryParam("nickname",nickname)
                    .execute().get().getResponseBody();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            return responseStr==null?null
                    :objectMapper.readValue(responseStr, Person.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    protected Person[] getPersonList() {
        String responseStr = null;
        try {
            responseStr = personListbuilder.execute().get().getResponseBody();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            return responseStr==null?null
                    :objectMapper.readValue(responseStr, Person[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        hashOps = stringTemplate.opsForHash();
    }
}
