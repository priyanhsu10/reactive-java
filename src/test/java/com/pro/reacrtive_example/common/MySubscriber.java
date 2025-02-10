package com.pro.reacrtive_example.common;



import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySubscriber<T> implements Subscriber<T> {
    private static Logger log = LoggerFactory.getLogger(MySubscriber.class);
    private final String name ;
    private Subscription subscription;

    public MySubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription=subscription;
        subscription.request(Integer.MAX_VALUE);

    }

    @Override
    public  void onNext(T item) {
        log.info("{}: received :{}",name,item);

    }

    @Override
    public void onError(Throwable throwable) {
        log.error("{} : error",name,throwable);

    }

    @Override
    public void onComplete() {

        log.info("{} :Completed",name);
    }

    public Subscription getSubscription() {
        return subscription;
    }
}
