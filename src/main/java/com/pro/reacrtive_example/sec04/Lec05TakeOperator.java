package com.pro.reacrtive_example.sec04;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

public class Lec05TakeOperator {
    public static void main(String[] args) {
//        take();
        //takeWhile();
        takeUntil();
    }
    public  static  void take(){
        Flux.range(1,10)
                .log("take")
                .take(3)
                .log("sub")
                .subscribe(Util.subscriber());
    }
    public static void takeWhile(){
        Flux.range(1,19)
                .log("take")
                .takeWhile(x->x<10) //stop when condition is not met
                .log("sub")
                .subscribe(Util.subscriber());
    }
    public static void takeUntil(){
        Flux.range(1,19)
                .log("take")
                .takeUntil(x->x==10) //stop when condition is met
                .log("sub")
                .subscribe(Util.subscriber());
    }
}
