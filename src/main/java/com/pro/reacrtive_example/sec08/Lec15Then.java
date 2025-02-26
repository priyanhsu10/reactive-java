package com.pro.reacrtive_example.sec08;

import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class Lec15Then {

    private static final Logger log = LoggerFactory.getLogger(Lec15Then.class);

    public static void main(String[] args) {
        var record = List.of("q", "b", "c");
        saveRecord(record)
                .map(x->{
                    log.info(x);
                    return  x;
                })
                .then(sendNotificaton(record))
                .subscribe(Util.subscriber());
//sendNotificaton(record)
//        .subscribe(Util.subscriber("notificatin"));
        Util.sleepSeconds(5);
        Util.sleepSeconds(5);


    }

    private static Mono<Void> sendNotificaton(List<String> record) {
        return Mono.fromRunnable(() -> log.info("all the {} send scuccesully", record));

    }

    private static Flux<String> saveRecord(List<String> record) {
        return Flux.fromIterable(record)
                .map(x -> "saved -" + x)
                .delayElements(Duration.ofMillis(400));

    }

    public static Flux<Integer> fallback() {
        return Flux.just(1);
    }
}
