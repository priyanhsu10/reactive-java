package com.pro.reacrtive_example.sec09;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class Lec02Assignment {
    record BookOrder(String genre, String title, Integer price) {
    }

    ;


    private static Flux<BookOrder> bookOrderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(x -> new BookOrder(Util.faker.book().genre(), Util.faker.book().title(), Util.faker.random().nextInt(100, 300)));
    }

    public static void main(String[] args) {
        var allowedCategories= List.of("Science fiction","Fantasy","Biography/Autobiography");
        bookOrderStream()
                .filter(f->allowedCategories.contains(f.genre))
                .buffer(Duration.ofSeconds(5))
                .map(x -> x.stream()
                        .collect(Collectors.groupingBy(y -> y.genre, Collectors.summingInt(
                        BookOrder::price
                )))).subscribe(Util.subscriber());
        Util.sleepSeconds(50);
    }
}
