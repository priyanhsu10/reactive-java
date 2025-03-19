package com.pro.reacrtive_example.sec10;

import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec02Retry {
    private final static Logger log = LoggerFactory.getLogger(Lec02Retry.class);

    public static void main(String[] args) {
        //demo1();
        demo2();
        Util.sleepSeconds(10);
    }

    private static Mono<String> getCoutryName() {
        var atomicint = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {

                    if (atomicint.getAndIncrement() < 3) {
                        throw new RuntimeException("oops");
                    }
                    return Util.faker.company().name();
                })
                .doOnError(err -> log.info("Error: {}", err.getMessage()))
                .doOnSubscribe((s) -> log.info("subscribing"));

    }

    private static void demo1() {
        getCoutryName()
                .retry(4)
                .subscribe(Util.subscriber());

    }

    private static void demo2() {
        getCoutryName()
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(1)).
                                doBeforeRetry(rs -> log.info("retrying {}", rs.totalRetries()))
                                .onRetryExhaustedThrow((spec,signal)->signal.failure()))
                .filter(ex -> RuntimeException.class.equals(ex.getClass()))
                .subscribe(Util.subscriber());
    }
}


