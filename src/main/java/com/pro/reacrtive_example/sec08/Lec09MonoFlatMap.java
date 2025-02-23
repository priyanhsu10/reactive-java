package com.pro.reacrtive_example.sec08;

import com.pro.reacrtive_example.common.Util;
import com.pro.reacrtive_example.sec08.application.PaymentService;
import com.pro.reacrtive_example.sec08.application.UserService;

public class Lec09MonoFlatMap {
    public static void main(String[] args) {

        UserService
                .getUserId("mike")
                .flatMap(x-> PaymentService.getUserBalance(x))
                .subscribe(Util.subscriber());
    }
}
