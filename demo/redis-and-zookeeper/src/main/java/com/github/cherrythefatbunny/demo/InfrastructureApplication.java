package com.github.cherrythefatbunny.demo;

import com.github.cherrythefatbunny.demo.api.Person;
import com.github.cherrythefatbunny.demo.zookeeper.EmbeddedZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import redis.embedded.RedisServer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class InfrastructureApplication implements CommandLineRunner, EnvironmentAware {
    @Autowired
    @Qualifier("jsonReactiveTemplate")
    ReactiveRedisOperations<String, Person> ops;
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(InfrastructureApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(!environment.getProperty("infrastructure.enable",boolean.class,false))return;
        int port = environment.getProperty("embedded.zookeeper.port", int.class);
        new EmbeddedZooKeeper(port, false).start();

        RedisServer redisServer = null;
        try {
            redisServer = new RedisServer(environment.getProperty("embedded.redis.port", int.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        redisServer.start();
        //init redis data
        Person p1 = new Person();
        p1.setId(101);
        p1.setName("cherry1");
        Person p2 = new Person();
        p2.setId(102);
        p2.setName("cherry2");
        Map<String,Person> persons = new HashMap<>();
        persons.put(p1.getId()+"",p1);
        persons.put(p2.getId()+"",p2);
        ops.opsForHash().putAll("person",persons).subscribe();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}

