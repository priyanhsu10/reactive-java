package com.pro.reacrtive_example.sec06;

import com.pro.reacrtive_example.common.MySubscriber;
import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec02HotPublisher {
    public static void main(String[] args) {

        var movieFlux = moviewStream()
                .publish()
                .refCount(1);
        Util.sleepSeconds(2);
        MySubscriber<String> sam=(MySubscriber) Util.subscriber("sam");
        movieFlux.take(2).subscribe(sam);
        Util.sleepSeconds(3);
        MySubscriber<String> mike=(MySubscriber) Util.subscriber("mike");
        movieFlux
                .subscribe(mike);

        Util.sleepSeconds(2);
        sam.getSubscription().cancel();



        movieFlux.subscribe(Util.subscriber("priyanshu"));
        Util.sleepSeconds(2);
        sam.getSubscription().request(3);
        mike.getSubscription().cancel();
        Util.sleepSeconds(15);
    }

    private static Flux<String> moviewStream() {
        return Flux.generate(() -> {
                            System.out.println("reviced the requst");
                            return 1;
                        },

                        (state, sink) -> {
                            var scene = "movie scene " + state;
                            sink.next(scene);
                            System.out.println("playing : +" + scene);
                            return ++state;
                        })
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}
