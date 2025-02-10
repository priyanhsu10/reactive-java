package com.pro.reacrtive_example.sec03;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

public class Lec02MultipleFluxJust {
    public static void main(String[] args) {
       Flux<Integer> flux= Flux.just(1,2,3,4,5,6);


        flux.subscribe(Util.subscriber("sub1"));
        flux
                .filter(x->x%2==0)
                .subscribe(Util.subscriber("sub2"));

    }
}
