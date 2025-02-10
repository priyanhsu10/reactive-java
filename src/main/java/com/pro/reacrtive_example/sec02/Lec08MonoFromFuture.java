package com.pro.reacrtive_example.sec02;

import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec08MonoFromFuture {
    private static final Logger log = LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    public static void main(String[] args) {

        var mono = Mono.fromFuture(() -> getName());
        mono.subscribe(Util.subscriber());
        Util.sleepSeconds(1);

    }

    public static CompletableFuture<String> getName() {


        return CompletableFuture.supplyAsync(() -> {
            log.info("geting  name ");
            return "priyanshu";
        });
    }
}
