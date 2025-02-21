package com.pro.reacrtive_example.sec08.helper;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class AmericanAirlines {
    private static final String AIRLINE = "AmericanAirlines";

    public  static Flux<Flight> getFlights() {
        return Flux.range(1, Util.faker.random().nextInt(2, 10))
                .delayElements(Duration.ofMillis(Util.faker.random().nextInt(300, 1000)))
                .map(x -> new Flight(AIRLINE, Util.faker.random().nextInt(300, 1000)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
