package com.pro.reacrtive_example.sec08;


import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03ConcatWith {
    private static final Logger log = LoggerFactory.getLogger(Lec03ConcatWith.class);

    public static void main(String[] args) {

//        demo1();
//    demo2();
//    demo3();
        demo4();
    }
    private static void demo2() {
        p1().concatWithValues(-1,0)
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }
    private static void demo4() {
      Flux.concat(getName(),getName(),p1(),p2())
              .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }
    private static void demo3() {
        p1().concatWith(p2())
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }
    private static Flux<Integer> p1(){
        return  Flux.just(1,2,3)
                .doOnSubscribe( s->log.info("subscribing to producer1"))
                .delayElements(Duration.ofMillis(10));

    }

    private static Flux<Integer> p2(){
        return  Flux.just(51,52,53)
                .doOnSubscribe( s->log.info("subscribing to producer2"))
                .delayElements(Duration.ofMillis(10));

    }
    private static void demo1() {
        getName().concatWith(getCity())
                .subscribe(Util.subscriber());
    }

    private static Flux<String> getName() {
        return Flux.<String>generate(sink -> {

            var name = Util.faker.name().name();
            sink.next(name);
        }).take(3);


    }
    private static Flux<String> getCity() {
        return Flux.<String>generate(sink -> {

            var name = Util.faker.address().city();
            sink.next(name);
        }).take(2);


    }
}
