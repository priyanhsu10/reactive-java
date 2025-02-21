package com.pro.reacrtive_example.sec08.helper;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class Kayak {
    public static Flux<Flight> getFlights() {
        return Flux.merge(AmericanAirlines.getFlights(),
                        Emirate.getFlights(),
                        Qatar.getFlights(),
                        Indigo.getFlights())
                .take(Duration.ofSeconds(2));
    }
}
