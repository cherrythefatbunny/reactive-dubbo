package com.github.cherrythefatbunny.demo.api;

import java.util.List;

public interface PersonService {
    List<String> getPersonNameList();
    String getPersonNameById(int id);
}
