package com.github.cherrythefatbunny.demo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.cherrythefatbunny.api.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    @Reference
    PersonService personService;
    @GetMapping
    public List<String> getPersonNameList() {
        return personService.getPersonNameList();
    }
    @GetMapping("by")
    public String getPersonNameById(@RequestParam int id) {
        return personService.getPersonNameById(id);
    }
}
