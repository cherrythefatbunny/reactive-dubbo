package com.github.cherrythefatbunny.demo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.cherrythefatbunny.api.PersonService;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Override
    public List<String> getPersonNameList() {
        return null;
    }

    @Override
    public String getPersonNameById(int id) {
        return null;
    }
}
