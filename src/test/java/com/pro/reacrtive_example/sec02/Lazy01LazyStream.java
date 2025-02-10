package com.pro.reacrtive_example.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class Lazy01LazyStream {
    private static final Logger log= LoggerFactory.getLogger(Lazy01LazyStream.class);

    public static void main(String[] args) {
        Stream.of(1).peek(i->log.info("recieved: {}",i))
                .toList();
    }
}
