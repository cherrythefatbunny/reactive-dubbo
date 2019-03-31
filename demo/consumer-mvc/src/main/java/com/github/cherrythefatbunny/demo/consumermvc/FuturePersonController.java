package com.github.cherrythefatbunny.demo.consumermvc;

import com.github.cherrythefatbunny.demo.api.FuturePersonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequestMapping("future")
@RestController
public class FuturePersonController {
    @Reference
    FuturePersonService futurePersonService;
    @GetMapping("by")
    public CompletableFuture<String> getPersonNameById(@RequestParam int id) {
        return futurePersonService.getPersonNameById(id);
    }
    @GetMapping("list")
    public CompletableFuture<List<String>> getPersonNameList() {
        return futurePersonService.getPersonNameList();
    }
}
