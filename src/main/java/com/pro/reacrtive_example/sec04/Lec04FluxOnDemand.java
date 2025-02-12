package com.pro.reacrtive_example.sec04;

import com.pro.reacrtive_example.common.Util;
import com.pro.reacrtive_example.sec01.subscriber.SubscriberImpl;
import reactor.core.publisher.Flux;

public class Lec04FluxOnDemand {


    public static void main(String[] args) {
//        produceEarly();
        produceOnDemand();
    }

    public static void produceEarly() {


        var subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                var name = Util.faker.name().firstName();
                System.out.println(i + ". genearted name: " + name);
                fluxSink.next(name);
            }
            fluxSink.complete();
        }).subscribe(subscriber);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
    }

    public static void produceOnDemand() {
        SubscriberImpl subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {

            fluxSink.onRequest(x -> {
                for (int i = 0; i < x && !fluxSink.isCancelled(); i++) {
                    var name = Util.faker.name().name();
                    System.out.println(i + ". generated name " + name);
                    fluxSink.next(name);
                }
            });
        }).subscribe(subscriber);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(5);
        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
    }

}
