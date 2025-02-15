package com.pro.reacrtive_example.sec04.helper;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameGenerator implements Consumer<FluxSink<String>> {

    FluxSink<String> stringFluxSink;
    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        System.out.println("got the flux sink");
        this.stringFluxSink= stringFluxSink;
    }
    public  void  generate(){
        this.stringFluxSink.next(Util.faker.company().name());
    }
}
