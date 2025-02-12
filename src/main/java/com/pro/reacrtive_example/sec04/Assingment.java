package com.pro.reacrtive_example.sec04;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

public class Assingment {
    public static void main(String[] args) {

        Flux.<String>generate(synchronousSink -> {

                    var contry = Util.faker.country().name();
                    synchronousSink.next(contry);
                })
                .takeUntil(x -> x.equalsIgnoreCase("Canada"))
                .subscribe(Util.subscriber());
    }
}
