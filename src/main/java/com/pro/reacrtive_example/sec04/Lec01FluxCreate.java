package com.pro.reacrtive_example.sec04;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            for(int i=0;i<10;i++){
                Util.sleepSeconds(1);
                fluxSink.next(Util.faker.name().firstName());
            }
            fluxSink.complete();;
        }).subscribe(Util.subscriber());
    }
}
