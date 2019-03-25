package com.github.cherrythefatbunny.demo.consumermvc;

import com.github.cherrythefatbunny.demo.api.PersonService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    @Reference
    PersonService personService;
    @GetMapping("by")
    public String getPersonNameById(@RequestParam int id) {
        return personService.getPersonNameById(id);
    }
    @GetMapping("list")
    public List<String> getPersonNameList() {
        return personService.getPersonNameList();
    }
}
