package com.pro.reacrtive_example.sec08;

import com.pro.reacrtive_example.common.Util;
import com.pro.reacrtive_example.sec08.application.OrderService;
import com.pro.reacrtive_example.sec08.application.UserService;
import reactor.core.scheduler.Schedulers;

public class Lec11FluxFlatMap {
    public static void main(String[] args) {

        UserService.getAllUser()
                .map(x->x.id())
                .flatMap(u-> OrderService.getUserOrder(u))
                .log()
                .subscribeOn(Schedulers.boundedElastic())
                .sort((x,y)->x.userId().compareTo(y.userId()))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
