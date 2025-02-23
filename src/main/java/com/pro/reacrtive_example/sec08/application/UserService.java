package com.pro.reacrtive_example.sec08.application;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class UserService {
    private static Map<String, Integer> userTable = Map.of(
            "sam", 1,
            "mike", 2,
            "jake", 3
    );

    public static Flux<User> getAllUser() {
        return Flux.fromIterable(userTable.entrySet())
                .map(x -> new User(x.getKey(), x.getValue()));
    }

    public static Mono<Integer> getUserId(String username) {
        return Mono.fromSupplier(() -> userTable.get(username));
    }
}
