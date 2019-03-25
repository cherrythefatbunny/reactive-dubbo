package com.github.cherrythefatbunny.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {
    @Autowired
    RedisOperations<String,String> stringTemplate;
    HashOperations<String,String,String> hashOps;

    @Autowired
    ReactiveRedisOperations<String, String> stringReactiveTemplate;
    ReactiveHashOperations<String,String,String> reactiveHashOps;

    @Before
    public void before() {
        hashOps = stringTemplate.opsForHash();
        reactiveHashOps = stringReactiveTemplate.opsForHash();
    }
    @Test
    public void redisTest1() {
        Map<String,String> map = hashOps.entries("nickname");
        Assert.assertEquals("cherry1",map.get("101"));
        Assert.assertEquals("cherry2",map.get("102"));
    }

    @Test
    public void redisTest2() {
        String nickname = hashOps.get("nickname","101");
        Assert.assertEquals("cherry1",nickname);
    }
    @Test
    public void redisTest3() {
        String nickname = hashOps.get("nickname","100");
        Assert.assertNull(nickname);
    }
    @Test
    public void redisTest4() {
        StepVerifier.create(reactiveHashOps.values("nickname"))
                .expectNextSequence(Arrays.asList("cherry1","cherry2"))
                .expectComplete()
                .verify();
    }
    @Test
    public void redisTest5() {
        StepVerifier.create(reactiveHashOps.get("nickname","101"))
                .expectNext("cherry1")
                .expectComplete()
                .verify();
    }
    @Test
    public void redisTest6() {
        StepVerifier.create(reactiveHashOps.get("nickname","100"))
                .expectComplete()
                .verify();
    }
}

