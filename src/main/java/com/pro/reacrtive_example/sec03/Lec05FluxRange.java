package com.pro.reacrtive_example.sec03;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {
    public static void main(String[] args) {

        Flux.range(1,10)
                .filter(x->x%2==1)
                .subscribe(Util.subscriber());
    }
}
