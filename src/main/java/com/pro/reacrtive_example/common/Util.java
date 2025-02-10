package com.pro.reacrtive_example.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Util {

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
}
