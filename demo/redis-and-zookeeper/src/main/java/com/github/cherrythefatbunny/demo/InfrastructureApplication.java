package com.github.cherrythefatbunny.demo;

import com.github.cherrythefatbunny.api.Person;
import com.github.cherrythefatbunny.demo.zookeeper.EmbeddedZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import redis.embedded.RedisServer;

import java.io.IOException;

@SpringBootApplication
public class InfrastructureApplication implements CommandLineRunner, EnvironmentAware {
    @Autowired
    ReactiveRedisOperations<String, Person> ops;
    private ReactiveRedisConnection connection;
    private static final String PREFIX = InfrastructureApplication.class.getSimpleName();
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(InfrastructureApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int port = environment.getProperty("embedded.zookeeper.port", int.class);
        new EmbeddedZooKeeper(port, false).start();

        RedisServer redisServer = null;
        try {
            redisServer = new RedisServer(environment.getProperty("embedded.redis.port", int.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        redisServer.start();

        Person p1 = new Person();
        p1.setId(101);
        p1.setName("cherry1");
        ops.opsForValue().set("person:"+p1.getId(),p1).subscribe();

        Person p2 = new Person();
        p2.setId(102);
        p2.setName("cherry2");
        ops.opsForValue().set("person:"+p2.getId(),p2).subscribe();

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}

