package com.github.cherrythefatbunny.demo;

import com.github.cherrythefatbunny.demo.api.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {
    @Autowired
    @Qualifier("jsonTemplate")
    RedisOperations<String,Person> ops;
    HashOperations<String,String,Person> hashOps;

    @Autowired
    @Qualifier("jsonReactiveTemplate")
    ReactiveRedisTemplate<String, Person> reactiveOps;
    ReactiveHashOperations<String,String,Person> reactiveHashOps;

    @Before
    public void before() {
        hashOps = ops.opsForHash();
        reactiveHashOps = reactiveOps.opsForHash();
    }
    @Test
    public void redisTest1() {
        Map<String,Person> map = hashOps.entries("person");
        Assert.assertEquals("cherry1",map.get("101").getName());
        Assert.assertEquals("cherry2",map.get("102").getName());
    }

    @Test
    public void redisTest2() {
        Person p = hashOps.get("person","101");
        Assert.assertEquals(101,p.getId());
        Assert.assertEquals("cherry1",p.getName());
    }
    @Test
    public void redisTest3() {
        Person p = hashOps.get("person","100");
        Assert.assertNull(p);
    }
    @Test
    public void redisTest4() {
        StepVerifier.create(reactiveHashOps.values("person").map(Person::getName))
                .expectNextSequence(Arrays.asList("cherry1","cherry2"))
                .expectComplete()
                .verify();
    }
    @Test
    public void redisTest5() {
        StepVerifier.create(reactiveHashOps.get("person","101").map(Person::getName))
                .expectNext("cherry1")
                .expectComplete()
                .verify();
    }
    @Test
    public void redisTest6() {
        StepVerifier.create(reactiveHashOps.get("person","100"))
                .expectComplete()
                .verify();
    }
}

