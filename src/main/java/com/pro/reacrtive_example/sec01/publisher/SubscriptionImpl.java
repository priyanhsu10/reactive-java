package com.pro.reacrtive_example.sec01.publisher;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {
    private static Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);
    private boolean isCancel;
    private int count = 0;
    private final int MAX_ITEMS = 10;
    private final Subscriber<? super String> subscriber;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void request(long l) {
        if (isCancel) {
            return;
        }
        log.info("subscriber hase request :{}", l);
        if (l > MAX_ITEMS) {
            this.subscriber.onError(new RuntimeException("Validation failed"));
          isCancel=true;
            return;
        }
        for (int i = 0; i < l && count < MAX_ITEMS; i++) {
            count++;
            subscriber.onNext("email-" + i);
        }
        if (count == MAX_ITEMS) {
            log.info("No more item to produce");
            subscriber.onComplete();
            this.isCancel = true;
        }
    }

    @Override
    public void cancel() {
        log.info("subscriber has cancelled");
        isCancel = true;


    }
}
