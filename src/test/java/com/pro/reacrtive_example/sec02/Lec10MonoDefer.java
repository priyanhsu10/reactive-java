package com.pro.reacrtive_example.sec02;

import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec10MonoDefer {
    private  static  final Logger log= LoggerFactory.getLogger(Lec10MonoDefer.class);
    public static void main(String[] args) {
//        createPublisher().subscribe(Util.subscriber());
        Mono.defer(Lec10MonoDefer::createPublisher)
                .subscribe(Util.subscriber());
    }

    private  static Mono<Integer> createPublisher(){
      log.info("creating publisher");
      var list= List.of(1,2,3);
        Util.sleepSeconds(2);
        return  Mono.fromSupplier(()->sum(list));

    }
    private static  int sum(List<Integer> items){
        log.info("finding sum of {}", items);
        Util.sleepSeconds(3);
        return  items.stream().mapToInt(x->x).sum();
    }
}
