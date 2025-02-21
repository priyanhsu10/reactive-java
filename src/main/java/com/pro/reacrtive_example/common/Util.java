package com.pro.reacrtive_example.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.UnaryOperator;

public class Util {
private final static Logger log= LoggerFactory.getLogger(Util.class);
    public  static  <T>Subscriber<T> subscriber(){
        return  new MySubscriber<T>("");
    }
    public  static  <T> Subscriber<T> subscriber(String name){
        return new MySubscriber<>(name);

    }

    public static Faker faker = new Faker();
    public static void main(String[] args) {
        var mono= Mono.just(1);
        mono.subscribe(subscriber("test"));
        mono.subscribe(subscriber("test1"));
    }

    public static  void sleepSeconds(int sec){

        try {
            Thread.sleep(Duration.ofSeconds(sec));
        } catch (InterruptedException e) {
        }
    }
    public  static  <T>UnaryOperator<Flux<T>> fluxLogger(String name){

        return  flux->flux
                .doOnSubscribe(s->log.info("subscribing to {}", name))
                .doOnCancel(()->log.info("canceling {}",name))
                .doOnComplete(()->log.info("{} completed",name));
    }
}
