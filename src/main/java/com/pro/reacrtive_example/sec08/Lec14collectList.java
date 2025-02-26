package com.pro.reacrtive_example.sec08;

import com.pro.reacrtive_example.common.Util;
import com.pro.reacrtive_example.sec08.assignment.ProductHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec14collectList {
    public static void main(String[] args) {

        var t= Flux.range(1, 10)
                .concatWith(Flux.error(new RuntimeException("oops")))
                .onErrorResume((e)->fallback())
                        .collectList();


t.subscribe(Util.subscriber());
        Util.sleepSeconds(10);
    }
    public  static  Flux<Integer> fallback(){
        return  Flux.just(1);
    }
}
