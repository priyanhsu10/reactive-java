package com.pro.reacrtive_example.sec05;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

//filter +map
public class Lec01Handle {
    public static void main(String[] args) {


        Flux.range(1,10)
                .filter(x->x!=7)
                .handle((item,sink)->{

                    switch (item){
                        case 1->sink.next(-2);
                        case 4->{}
                        case 7->sink.error(new RuntimeException(" 7 is eror"));
                        default -> sink.next(item);
                    }
                })
                .subscribe(Util.subscriber());
    }
}
