package com.pro.reacrtive_example.sec08;

import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05Merge {
    private static final Logger log= LoggerFactory.getLogger(Lec05Merge.class);

    public static void main(String[] args) {

//        demo1();
        demo2();
    }
    private static void demo2() {
     p1().mergeWith(p2())
                .take(2)
                .subscribe(Util.subscriber());
        Util.sleepSeconds(6);
    }
    private static void demo1() {
        Flux.merge(p1(),p2())
                .take(2)
                .subscribe(Util.subscriber());
        Util.sleepSeconds(6);
    }

    private static Flux<Integer> p1() {
        return Flux.just(1, 2, 3)
                .transform(Util.fluxLogger("p1"))
                .delayElements(Duration.ofMillis(10));

    }

    private static Flux<Integer> p2() {
        return Flux.just(51, 52, 53,12,12,22,4)
                .transform(Util.fluxLogger("p2"))
                .delayElements(Duration.ofMillis(20));

    }
}
