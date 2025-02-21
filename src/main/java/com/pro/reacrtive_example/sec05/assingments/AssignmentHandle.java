package com.pro.reacrtive_example.sec05.assingments;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

public class AssignmentHandle {

    public static void main(String[] args) {

        Flux.<String>generate(synchronousSink ->
                        synchronousSink.next(Util.faker.country().name()))
                .handle((item, sink) ->
                {
                    sink.next(item);

                    if (item.equalsIgnoreCase("canada")) {
                        sink.complete();
                    }
                })
                .subscribe(Util.subscriber());
    }
}
