package com.pro.reacrtive_example.sec04;

import com.pro.reacrtive_example.common.Util;
import com.pro.reacrtive_example.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec02fluxRefactor {
    public static void main(String[] args) {

        NameGenerator generator= new NameGenerator();
        Flux.create(generator)
                .subscribe(Util.subscriber());

        Flux.range(1,10)
                .subscribe(x-> generator.generate());
    }
}
