package com.pro.reacrtive_example.sec08;

import com.pro.reacrtive_example.common.Util;
import com.pro.reacrtive_example.sec08.application.OrderService;
import com.pro.reacrtive_example.sec08.application.UserService;
import com.pro.reacrtive_example.sec08.assignment.ProductHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec13concatMap {
    public static void main(String[] args) {

        ProductHttpClient httpClient= new ProductHttpClient();

        Flux.range(1,10)
                .concatMap(x->httpClient.getProduct(x) )
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }
}
