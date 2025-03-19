package com.pro.reacrtive_example.sec10;

import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec03ExternalServiceDemo {
    private static Logger log= LoggerFactory.getLogger(Lec03ExternalServiceDemo.class);
    public static void main(String[] args) {
//        repeat();
        retry();
        Util.sleepSeconds(60);
    }

    private static void repeat() {
        var client = new ExternalProductClient();
        client.getCountry()
                .repeat()
                .takeUntil(x -> x.equalsIgnoreCase("Japan"))
                .subscribe(Util.subscriber());
    }
    private static void retry() {
        var client = new ExternalProductClient();
        client.getProductName(2)
                .retryWhen(getRetrySpec())
                .subscribe(Util.subscriber());
    }
    private static Retry getRetrySpec(){
        return  Retry.fixedDelay(20, Duration.ofSeconds(1))
                .filter(ex->ServerError.class.equals(ex.getClass()))
                .doBeforeRetry(rs->log.info("retrying {}",rs.failure().getMessage()));

    }
}
