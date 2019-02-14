package com.github.cherrythefatbunny.demo.api;

import reactor.core.publisher.Mono;

import java.util.List;

public interface PersonService {
    List<String> getPersonNameList();
    String getPersonNameById(int id);
}
