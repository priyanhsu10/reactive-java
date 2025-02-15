package com.pro.reacrtive_example.sec05;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

public class Lec08SwitchIfempty {

    //use case
    //postgres +redis
    // check with cache
    // if empty then call postgres as fallback
    public static void main(String[] args) {
        Flux.range(1,10)
                .filter(x->x>10)
                .switchIfEmpty(getFallback())
                .subscribe(Util.subscriber());
    }

    public  static Flux<Integer> getFallback(){
        return  Flux.range(10,4);
    }
}
