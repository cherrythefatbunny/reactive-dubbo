package com.github.cherrythefatbunny.demo.provider;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProviderApplicationTests {
    @Autowired
    RedisOperations<String, String> ops;
    HashOperations<String,String,String> hashOps;

    @Before
    public void before() {
        hashOps = ops.opsForHash();
    }
    @Test
    public void redisTest1() {
        List<String> nameList = hashOps.values("person");
        Assert.assertEquals("cherry1",nameList.get(0));
        Assert.assertEquals("cherry2",nameList.get(1));
    }

    @Test
    public void redisTest2() {
        String name = hashOps.get("person","101");
        Assert.assertEquals("cherry1",name);
    }
    @Test
    public void redisTest3() {
        String name = hashOps.get("person","100");
        Assert.assertNull(name);
    }
}

