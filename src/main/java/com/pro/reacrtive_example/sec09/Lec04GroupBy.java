package com.pro.reacrtive_example.sec09;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec04GroupBy {
    public static void main(String[] args) {
        Flux.range(1,30)
                .delayElements(Duration.ofSeconds(1))
                .map(x->x*2)
                .groupBy(i->i%2)
                .flatMap(x->processEvent(x))
                .subscribe();
        Util.sleepSeconds(60);
    }
    private static Mono<Void> processEvent(GroupedFlux<Integer,Integer> gpflex){
        System.out.println("recieved flux for "+ gpflex.key());
        return gpflex.doOnNext(x-> System.out.println("key :"+gpflex.key() + " itme :"+x))
                .then();
    }
}
