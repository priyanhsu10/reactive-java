package com.pro.reacrtive_example.sec03;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class Lec04FluxFromStream {
    public static void main(String[] args) {
        var list = List.of(3, 1, 2, 4, 5, 6, 7);

        Stream<Integer> stream = list.stream();
        var flux = Flux.fromStream(stream);


        flux.subscribe(Util.subscriber());
        flux.subscribe(Util.subscriber("sub1"));
    }
}
