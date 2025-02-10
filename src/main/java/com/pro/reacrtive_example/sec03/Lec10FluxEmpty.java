package com.pro.reacrtive_example.sec03;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

public class Lec10FluxEmpty {
    public static void main(String[] args) {
        Flux.empty()
                .subscribe(Util.subscriber());
    }
}
