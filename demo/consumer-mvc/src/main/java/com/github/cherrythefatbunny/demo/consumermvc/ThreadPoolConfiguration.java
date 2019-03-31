package com.github.cherrythefatbunny.demo.consumermvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@EnableAsync
@Configuration
public class ThreadPoolConfiguration {
    @Bean
    public ExecutorService executorService() {
        return new ThreadPoolExecutor(5,200,60, TimeUnit.SECONDS,new SynchronousQueue<>());
    }
}
