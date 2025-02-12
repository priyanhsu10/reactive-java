package com.pro.reacrtive_example.sec04;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerate {
    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {

                    synchronousSink.next(1);
                    synchronousSink.error(new RuntimeException("oops"));
                })
                .take(20)
                .subscribe(Util.subscriber());
    }
}
