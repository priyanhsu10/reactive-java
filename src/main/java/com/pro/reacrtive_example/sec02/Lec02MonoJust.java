package com.pro.reacrtive_example.sec02;

import com.pro.reacrtive_example.sec01.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

public class Lec02MonoJust {
    public static void main(String[] args) {
        Mono<String> mono=Mono.just(" ---just ");
        var subscriber=new SubscriberImpl();
        mono.subscribe(subscriber);
        subscriber.getSubscription().request(10);
        subscriber.getSubscription().request(10);
        System.out.println(mono);

    }
}
