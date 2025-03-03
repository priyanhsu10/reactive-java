package com.pro.reacrtive_example.sec10;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec01Repeat {
    public static void main(String[] args) {
        //demo1();
        //demo2();
       // demo3();
      demo4();
    }

    public static Mono<String> getContryName() {
        return Mono.fromSupplier(() -> Util.faker.country().name());
    }

    public static void demo1() {
        getContryName().repeat(5)
                .subscribe(Util.subscriber());
    }
    public  static  void demo3(){
        var aatomicInteger=new AtomicInteger(0);
        getContryName()
                .repeat(()->aatomicInteger.incrementAndGet()<3)
                .subscribe(Util.subscriber());
    }
  public  static  void demo2(){
        getContryName()
                .repeat()
                .takeUntil(x->x.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
  }
  public static  void demo4(){
        getContryName()
                .repeatWhen(flux->flux.delayElements(Duration.ofSeconds(1)).take(10))
                        .subscribe(Util.subscriber());
        Util.sleepSeconds(10);
  }

}
