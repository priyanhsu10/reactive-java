package com.pro.reacrtive_example.sec03;

import com.pro.reacrtive_example.common.ExternalServiceClient;
import com.pro.reacrtive_example.common.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

public class Assingment {
    public static void main(String[] args) {

        ExternalServiceClient externalServiceClient = new ExternalServiceClient();
        int balance = 1000;
        int quantity = 0;
        externalServiceClient.getStockPrices()
                .subscribe(new StockSubscriber());
        Util.sleepSeconds(20);

    }
}

class StockSubscriber implements Subscriber<Integer> {
    private final static Logger log = LoggerFactory.getLogger(StockSubscriber.class);
    int balance = 1000;
    int quantity = 0;
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(Integer.MAX_VALUE);
    }

    @Override
    public void onNext(Integer stockPrice) {
        log.info("price : {}",stockPrice);
        if (stockPrice < 90 && balance > stockPrice) {
            quantity++;
            balance = balance - stockPrice;
            log.info("bought a stock at {} price , total quantity :{} remaining balance {}", stockPrice, quantity, balance);
        } else if (stockPrice > 110 && quantity > 0) {

            log.info("sellling quntity {} at price {}", quantity, stockPrice);
            balance = balance + quantity * stockPrice;
            quantity = 0;
            subscription.cancel();
            log.info("profit :{}",balance-1000);
        }

    }

    @Override
    public void onError(Throwable throwable) {
        log.info("encouter error");
    }

    @Override
    public void onComplete() {

        log.info("completed");
    }
}
