package com.pro.reacrtive_example.sec03;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec09FluxInterval {
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(500))
                .map(x->Util.faker.name().name())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(4);
    }
}
