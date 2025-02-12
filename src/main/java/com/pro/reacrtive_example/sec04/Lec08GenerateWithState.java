package com.pro.reacrtive_example.sec04;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

public class Lec08GenerateWithState {
    public static void main(String[] args) {

        Flux.generate(() -> 0,
                        (counter, sink) -> {
                            var country = Util.faker.country().name();
                            counter++;
                            sink.next(country);
                            if (counter == 10 || country.equalsIgnoreCase("canada")) {
                                sink.complete();
                            }
                            return counter;
                        })
                .subscribe(Util.subscriber());
    }
}
