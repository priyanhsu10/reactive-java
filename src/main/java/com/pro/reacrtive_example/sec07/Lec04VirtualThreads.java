package com.pro.reacrtive_example.sec07;

import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec04VirtualThreads {
    private static final Logger log = LoggerFactory.getLogger(Lec04VirtualThreads.class);

    public static void main(String[] args) {
        System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads","true");
        var flux = Flux.<Integer>create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generating : {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(x -> log.info("value: {}", x))
                .doFirst(() -> log.info("dofirst 1-{}",Thread.currentThread().isVirtual()))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("dofirst 2"));

        Runnable runnable=()-> flux.subscribe(Util.subscriber("sub1"));

        new Thread(runnable).start();
        Util.sleepSeconds(2);
    }
}
