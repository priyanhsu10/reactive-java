package com.pro.reacrtive_example.sec03;

import com.pro.reacrtive_example.common.MySubscriber;
import com.pro.reacrtive_example.common.Util;
import com.pro.reacrtive_example.sec01.subscriber.SubscriberImpl;
import com.pro.reacrtive_example.sec03.helper.NameHelper;

public class Lec07NameGenerator {
    public static void main(String[] args) {

//        System.out.println(NameHelper.nameGenerator(5));
//        NameHelper.fluxNameGenerator(5)
//                .subscribe(Util.subscriber());

        var subscriber=new SubscriberImpl();

        NameHelper.fluxNameGenerator(10).subscribe(subscriber);
        subscriber.getSubscription().request(3);
        subscriber.getSubscription().cancel();

    }
}
