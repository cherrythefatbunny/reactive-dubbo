package com.github.cherrythefatbunny.demo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.cherrythefatbunny.api.PersonService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService, InitializingBean {
    @Autowired
    RedisOperations<String, String> ops;
    HashOperations<String,String,String> hashOps;

    @Override
    public List<String> getPersonNameList() {
        return hashOps.values("person");
    }

    @Override
    public String getPersonNameById(int id) {
        return hashOps.get("person",id+"");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        hashOps = ops.opsForHash();
    }
}
