package com.github.cherrythefatbunny.demo.consumerwebflux;

import com.github.cherrythefatbunny.demo.api.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("blocking")
@RestController
public class BlockingPersonController {
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
