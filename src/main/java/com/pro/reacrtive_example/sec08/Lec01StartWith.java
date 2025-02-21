package com.pro.reacrtive_example.sec08;

import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

public class Lec01StartWith {
    private static Logger log = LoggerFactory.getLogger(Lec01StartWith.class);
    private static final ArrayList<String> redis = new ArrayList<String>();

    public static void main(String[] args) {
        generateNames().
        take(2)
                .subscribe(Util.subscriber("sam"));
        generateNames().take(3)
                .subscribe(Util.subscriber("MIke"));
        generateNames().take(2)
                .subscribe(Util.subscriber("jake"));
        Util.sleepSeconds(5);
    }

    public static Flux<String> generateNames() {

        return Flux.<String>generate(sink -> {

                    log.info("generating name");
                    Util.sleepSeconds(1);
                    var name = Util.faker.name().name();
                    redis.add(name);
                    sink.next(name);
                })
                .startWith(redis);

    }
}
