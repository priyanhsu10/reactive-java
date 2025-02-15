package com.pro.reacrtive_example.sec05;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04Delay {
    public static void main(String[] args) {

        Flux.range(1,10)
                .log()
                .doOnRequest((x)-> System.out.println("requested "+x))
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(12);
    }
}
