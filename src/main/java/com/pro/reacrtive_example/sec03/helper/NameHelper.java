package com.pro.reacrtive_example.sec03.helper;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

public class NameHelper {

    public  static List<String> nameGenerator(int count){

      return   IntStream.rangeClosed(1,count)
                .mapToObj(x-> generate())
                .toList();
    }

    private static String generate() {
        try {
            Thread.sleep(Duration.ofSeconds(1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Util.faker.name().name();
    }

    public  static Flux<String> fluxNameGenerator(int count){

        return Flux.range(1,count)
                .map(x->generate());
    }
}
