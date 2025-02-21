package com.pro.reacrtive_example.sec08;

import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04ConcatError {
    private static final Logger log = LoggerFactory.getLogger(Lec04ConcatError.class);

    public static void main(String[] args) {
//        demo2();
        demo3();

    }

    private static Flux<Integer> p3() {

        return Flux.error(new RuntimeException("oops"));

    }
    private static void demo3() {
      Flux.concatDelayError(p1(),p3(),p2())
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }
    private static void demo2() {
        p1().concatWith(p3())
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }

    private static Flux<Integer> p1() {
        return Flux.just(1, 2, 3)
                .doOnSubscribe(s -> log.info("subscribing to producer1"))
                .delayElements(Duration.ofMillis(10));

    }

    private static Flux<Integer> p2() {
        return Flux.just(51, 52, 53)
                .doOnSubscribe(s -> log.info("subscribing to producer2"))
                .delayElements(Duration.ofMillis(10));

    }
}
