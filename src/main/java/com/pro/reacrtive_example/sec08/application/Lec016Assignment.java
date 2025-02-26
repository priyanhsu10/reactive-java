package com.pro.reacrtive_example.sec08.application;

import com.pro.reacrtive_example.common.Util;
import com.pro.reacrtive_example.sec08.application.Order;
import com.pro.reacrtive_example.sec08.assignment.ProductHttpClient;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec016Assignment {
    record  UserInformationAssignment(Integer userId, String username, Integer balance, List<Order> orders){}

    public static void main(String[] args) {

        UserService.getAllUser().flatMap(x->getUserInfo(x))
                .subscribe(Util.subscriber());
        Util.sleepSeconds(4);

    }

    private  static Mono<UserInformationAssignment> getUserInfo(User user){

        return  Mono.zip(
                PaymentService.getUserBalance(user.id()),
                OrderService.getUserOrder(user.id()).collectList()
        ).map(t->new UserInformationAssignment(user.id(),user.name(),t.getT1(),t.getT2()));
    }

}
