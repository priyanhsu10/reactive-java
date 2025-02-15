package com.pro.reacrtive_example.sec05;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec09Timeout {

    public static void main(String[] args) {

        getProductName()
                .timeout(Duration.ofSeconds(2),fallback())
             //   .onErrorReturn("vfallback")
                .subscribe(Util.subscriber());

        Util.sleepSeconds(4);

    }
    private static Mono<String> getProductName(){
        return  Mono.fromSupplier(()->"service-"+ Util.faker.commerce().productName())
                .delayElement(Duration.ofSeconds(3));
    }

    private static Mono<String> fallback(){
        return  Mono.fromSupplier(()->"fallback-"+ Util.faker.commerce().productName())
                .delayElement(Duration.ofMillis(300))
                .doFirst(()-> System.out.println("do first"));
    }
}
