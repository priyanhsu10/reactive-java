package com.pro.reacrtive_example.sec08.assignment;

import com.pro.reacrtive_example.common.Util;
import com.pro.reacrtive_example.sec08.application.OrderService;
import com.pro.reacrtive_example.sec08.application.UserService;
import com.pro.reacrtive_example.sec08.assignment.ProductHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec12FluxFlatMapAssignment {

        public static void main(String[] args) {
            ProductHttpClient httpClient= new ProductHttpClient();

            Flux.range(1,10)
                    .flatMap(x->httpClient.getProduct(x) )
                    .subscribe(Util.subscriber());

            Util.sleepSeconds(10);
    }
}
