package com.pro.reacrtive_example.sec08.application;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class OrderService {
    private static Map<Integer , List<Order>> orderTable =Map.of(
            1,List.of(
                   new Order(1, Util.faker.commerce().productName(),Util.faker.random().nextInt(10,100)),
                    new Order(1, Util.faker.commerce().productName(),Util.faker.random().nextInt(10,100))
            ),
            2,List.of(
                    new Order(2, Util.faker.commerce().productName(),Util.faker.random().nextInt(10,100)),
                    new Order(2, Util.faker.commerce().productName(),Util.faker.random().nextInt(10,100)),
                    new Order(2, Util.faker.commerce().productName(),Util.faker.random().nextInt(10,100))
                    ),
            3,List.of()
    );
    public  static Flux<Order> getUserOrder(int userId){

        return  Flux.fromIterable(orderTable.get(userId))
                .delayElements(Duration.ofMillis(100));
    }
}
