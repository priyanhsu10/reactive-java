package com.pro.reacrtive_example.sec05;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

public class Lec07DefualtIfEmpty {

    public static void main(String[] args) {
        Flux.range(1,10)
                .filter(i->i>11)
                .defaultIfEmpty(50)
                .subscribe(Util.subscriber());
    }
}
