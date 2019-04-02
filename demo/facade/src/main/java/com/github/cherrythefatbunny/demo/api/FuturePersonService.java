package com.github.cherrythefatbunny.demo.api;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface FuturePersonService {
    CompletableFuture<List<String>> getPersonNameList();
    CompletableFuture<String> getPersonNameById(int id);
}
