package com.github.cherrythefatbunny.demo.provider;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.cherrythefatbunny.demo.api.EchoService;
import com.github.cherrythefatbunny.demo.api.Person;
import com.github.cherrythefatbunny.demo.api.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service(proxy = "reactivejavassist")
public class PersonServiceImpl implements PersonService, InitializingBean {
    @Autowired
    @Qualifier("jsonTemplate")
    RedisTemplate<String, Person> ops;
    @Reference(stub = "com.github.cherrythefatbunny.demo.provider.stub.EchoServiceStub")
    EchoService echoService;
    HashOperations<String,String,Person> hashOps;

    @Override
    public List<String> getPersonNameList() {
        List<String> nameList = new ArrayList<>();
        hashOps.values("person").stream().map(Person::getName).forEach(nameList::add);
        return nameList;
    }

    @Override
    public String getPersonNameById(int id) {
        //invoke reactive method locally
        echoService.echoMono("fuck PersonService#getPersonNameById")
                .doOnError(throwable -> log.error("echoMono",throwable))
                .subscribe(log::info);
        Person p = hashOps.get("person",id+"");
        return p==null?null:p.getName();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        hashOps = ops.opsForHash();
    }
}
