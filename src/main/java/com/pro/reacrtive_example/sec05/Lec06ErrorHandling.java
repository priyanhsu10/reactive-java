package com.pro.reacrtive_example.sec05;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06ErrorHandling {
    public static void main(String[] args) {
//        onErrorResume1();
//        onErrorReturn();
        onErrorContinue();
    }


    private static void onErrorResume1() {
        Mono.error(new RuntimeException("oops"))
                .onErrorResume(ArithmeticException.class,ex->fallback1())
                .onErrorResume(ex->fallback2())
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }
    private static void onErrorContinue() {
        Flux.range(1,10)
                .map(x->x==5 ?5/0:x)
                .onErrorContinue((ex,item)-> System.out.println("item :"+item +" exception :"+ex.getLocalizedMessage()))
                .subscribe(Util.subscriber());
    }

    private static void onErrorReturn(){
        Mono.just(5)
                .map(x->x==5 ? 5/0:x)
                .onErrorReturn(IllegalArgumentException.class,-1)
                .onErrorReturn(ArithmeticException.class,-2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }
    private  static  void onErrorResume(){
        Mono.error(new RuntimeException("oops"))
                .onErrorResume(ArithmeticException.class,ex->fallback1());
    }
    private  static  Mono<Integer> fallback1(){
        return Mono.fromSupplier(()-> Util.faker.random().nextInt(10,100));

    }

    private  static  Mono<Integer> fallback2(){
        return Mono.fromSupplier(()-> Util.faker.random().nextInt(100,1000));

    }
}
