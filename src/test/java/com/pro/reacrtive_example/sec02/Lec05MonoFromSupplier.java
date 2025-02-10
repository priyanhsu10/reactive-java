package com.pro.reacrtive_example.sec02;

import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec05MonoFromSupplier {
    public static final Logger log = LoggerFactory.getLogger(Lec05MonoFromSupplier.class);

    public static void main(String[] args) {

        var list = List.of(1, 3, 4);
//        var mono = Mono.just(sum(list));
        // we want to lazy as far we go use fromSuplier

        var mono= Mono.fromSupplier(()->sum(list));
        mono.subscribe(Util.subscriber());

    }

    private static int sum(List<Integer> items) {
        log.info("finding sum of integer {}", items);
        return items.stream().mapToInt(x -> x).sum();
    }


}
