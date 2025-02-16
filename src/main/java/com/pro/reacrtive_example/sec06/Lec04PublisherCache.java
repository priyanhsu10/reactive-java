package com.pro.reacrtive_example.sec06;

import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04PublisherCache {
    private static Logger logger = LoggerFactory.getLogger(Lec04PublisherCache.class);
    public static void main(String[] args) {
    var stockFlux=stockStream().replay(1).autoConnect(0);

     Util.sleepSeconds(4);
     logger.info("sam joiing");
     stockFlux.subscribe(Util.subscriber("sam"));
     Util.sleepSeconds(4);
     logger.info("mike joinig");
     stockFlux.subscribe(Util.subscriber("mike"));
     Util.sleepSeconds(15);


    }
    private  static Flux<Integer> stockStream(){

        return  Flux
                .generate(sink->sink.next(Util.faker.random().nextInt(10,100)))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(x->logger.info("emititing price: {}",x))
                .cast(Integer.class);

    }
}
