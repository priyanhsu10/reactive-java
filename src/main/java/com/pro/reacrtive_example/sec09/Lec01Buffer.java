package com.pro.reacrtive_example.sec09;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

//consider the publisher in very fast rate this could be product view envent click event
//all this events are comming from kafka and and consumer is not able to coup up with the one by one processing
//suppose we want to save it in database
// we can batching them and save in db in one shot
// for batching scenario Buffer operator help to batch them on base of count, time interval , and also combination of bot
//like buffer(count , timeoutDuration)
public class Lec01Buffer {

    public static void main(String[] args) {

//        demo1();
//        demo2();
        demo3();
    }

    private static void demo1() {
        eventStream()
                .buffer(3)
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }

    private static void demo2() {
        eventStream().buffer(Duration.ofMillis(500))
                .subscribe(Util.subscriber());
        Util.sleepSeconds(50);
    }

    private static void demo3() {
        eventStream().bufferTimeout(3, Duration.ofSeconds(1))
                .subscribe(Util.subscriber());
        Util.sleepSeconds(20);
    }

    private static Flux<String> eventStream() {
        return Flux.range(1, 10).delayElements(Duration.ofMillis(200)).map(x -> "event -" + x);


    }

}
