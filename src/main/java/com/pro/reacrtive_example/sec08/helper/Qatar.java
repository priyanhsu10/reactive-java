package com.pro.reacrtive_example.sec08.helper;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Qatar {
    private static final String AIRLINE = "Qatar";

    public  static Flux<Flight> getFlights() {
        return Flux.range(1, Util.faker.random().nextInt(2, 5))
                .delayElements(Duration.ofMillis(Util.faker.random().nextInt(300, 800)))
                .map(x -> new Flight(AIRLINE, Util.faker.random().nextInt(500, 800)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
