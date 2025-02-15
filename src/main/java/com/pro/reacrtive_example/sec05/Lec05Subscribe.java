package com.pro.reacrtive_example.sec05;

import reactor.core.publisher.Flux;

public class Lec05Subscribe {
    public static void main(String[] args) {

        Flux.range(1,10)
                .doOnNext((x)-> System.out.println(x*2))
                .doOnError(err-> System.out.println(err))
                .doOnComplete(()-> System.out.println("comppleted"))
                .subscribe();
    }
}
