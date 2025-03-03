package com.pro.reacrtive_example.sec09;


import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
//window will open new flux on every window (every 1 hr , every 1 day , duration)
public class Lec03Window {
    public static void main(String[] args) {
       eventStream().window(Duration.ofMillis(2000))
               .flatMap(Lec03Window::processEvents)
               .subscribe();
        Util.sleepSeconds(60);
    }
    private static Flux<String> eventStream(){

        return Flux.interval(Duration.ofMillis(500)).map(x->"event-"+(x+1));

    }

    private static Mono<Void> processEvents(Flux<String> flux){
        return flux.doOnNext(e-> System.out.print("x"))
                .doOnComplete(()-> System.out.println()).then();
    }


}
