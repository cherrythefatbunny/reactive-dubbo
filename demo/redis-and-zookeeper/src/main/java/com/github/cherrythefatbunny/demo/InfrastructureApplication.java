package com.github.cherrythefatbunny.demo;

import com.github.cherrythefatbunny.demo.api.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.ReactiveRedisOperations;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class InfrastructureApplication implements CommandLineRunner {
    @Autowired
    ReactiveRedisOperations<String, Person> jsonReactiveTemplate;
    @Autowired
    ReactiveRedisOperations<String, String> stringReactiveTemplate;

    public static void main(String[] args) {
        SpringApplication.run(InfrastructureApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //init redis data
        Person p1 = new Person();
        p1.setId(101);
        p1.setNickName("cherry1");
        p1.setFullName("User cherry1");
        Person p2 = new Person();
        p2.setId(102);
        p2.setNickName("cherry2");
        p2.setFullName("User cherry2");
        Map<String,String> nickNames = new HashMap<>();
        nickNames.put(p1.getId()+"",p1.getNickName());
        nickNames.put(p2.getId()+"",p2.getNickName());
        stringReactiveTemplate.opsForHash().putAll("nickname",nickNames).subscribe();

        Map<String,Person> persons = new HashMap<>();
        persons.put(p1.getNickName(),p1);
        persons.put(p2.getNickName(),p2);
        jsonReactiveTemplate.opsForHash().putAll("person",persons).subscribe();
    }
}

