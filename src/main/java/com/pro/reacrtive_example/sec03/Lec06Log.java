package com.pro.reacrtive_example.sec03;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

public class Lec06Log {
    public static void main(String[] args) {

        Flux.range(1,10)
                .log()
                .map(x->Util.faker.name().name())
                .log()
                .subscribe(Util.subscriber());
    }
}
