package com.github.cherrythefatbunny.demo.consumermvc;

import com.github.cherrythefatbunny.demo.api.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Slf4j
@RequestMapping("async")
@RestController
public class AsyncPersonController {
    @Autowired
    ExecutorService executorService;
    @Reference
    PersonService personService;
    @GetMapping("by")
    public DeferredResult<String> getPersonNameById(@RequestParam int id) {
        DeferredResult<String> result = new DeferredResult<>(3000L);
        executorService.execute(() -> {
            try {
                String name = personService.getPersonNameById(id);
                result.setResult(name);
            } catch (Exception ex) {
                log.error("AsyncPersonController::getPersonNameById"+id,ex);
                result.setErrorResult(ex);
            }
        });
        return result;
    }
    @GetMapping("list")
    public DeferredResult<List<String>> getPersonNameList() {
        DeferredResult<List<String>> result = new DeferredResult<>(3000L);
        executorService.execute(() -> {
            try {
                List<String> nameList = personService.getPersonNameList();
                result.setResult(nameList);
            } catch (Exception ex) {
                log.error("AsyncPersonController::getPersonNameList",ex);
                result.setErrorResult(ex);
            }
        });
        return result;
    }
}
