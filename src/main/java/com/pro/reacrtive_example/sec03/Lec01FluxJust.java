package com.pro.reacrtive_example.sec03;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxJust {
    public static void main(String[] args) {
        Flux.just(1,2,3,4,5,6)
                .filter(x->x%2==0)
                .subscribe(Util.subscriber());
    }
}
