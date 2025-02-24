package com.pro.reacrtive_example.sec04;

import com.pro.reacrtive_example.common.Util;
import com.pro.reacrtive_example.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec02fluxRefactor {
    public static void main(String[] args) {

        NameGenerator generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

        Flux.range(1, 2)
                .subscribe(x -> generator.generate());
    }
}
