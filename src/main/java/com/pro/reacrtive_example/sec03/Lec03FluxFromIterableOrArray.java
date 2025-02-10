package com.pro.reacrtive_example.sec03;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec03FluxFromIterableOrArray {
    public static void main(String[] args) {
        var list= List.of("a","b","c");
        Flux.fromIterable(list)
                .subscribe(Util.subscriber());
        Integer[] arr= new Integer[]{1,2,3,45,2};

        Flux.fromArray(arr).subscribe(Util.subscriber());

    }
}
