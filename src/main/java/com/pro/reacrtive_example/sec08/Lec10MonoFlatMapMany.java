package com.pro.reacrtive_example.sec08;

import com.pro.reacrtive_example.common.Util;
import com.pro.reacrtive_example.sec08.application.OrderService;
import com.pro.reacrtive_example.sec08.application.UserService;

public class Lec10MonoFlatMapMany {
    public static void main(String[] args) {

        UserService.getUserId("jake")
                .flatMapMany(u-> OrderService.getUserOrder(u))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
