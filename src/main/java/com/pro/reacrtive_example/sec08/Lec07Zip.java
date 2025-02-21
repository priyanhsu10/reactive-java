package com.pro.reacrtive_example.sec08;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.time.Duration;

public class Lec07Zip {

    public static void main(String[] args) {

        Flux.zip(getbody(),getEngine(),getTires())
                .map(x->new Car(x.getT1(),x.getT2(),x.getT3()))
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }
    private static Flux<String> getbody(){

        return  Flux.range(1,5)
                .map(x->"body-"+x)
                .delayElements(Duration.ofMillis(100));
    }

    record  Car(String body, String engine, String tires){}
    private static Flux<String> getEngine(){

        return  Flux.range(1,3)
                .map(x->"engine-"+x)
                .delayElements(Duration.ofMillis(200));
    }

    private static Flux<String> getTires(){

        return  Flux.range(1,10)
                .map(x->"tires-"+x)
                .delayElements(Duration.ofMillis(70));
    }
}
