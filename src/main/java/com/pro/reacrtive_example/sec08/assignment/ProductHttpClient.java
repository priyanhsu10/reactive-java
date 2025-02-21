package com.pro.reacrtive_example.sec06.assignment;

import com.pro.reacrtive_example.common.AbstractHttpClient;
import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Assingmentv1 {
    public static void main(String[] args) {

//        OrderService orderService = new OrderService();
//        Flux<OrderItem> orderItemFlux = orderService.orderStream();
//        InventoryService inventoryService = new InventoryService();
//        RevenueService revenueService = new RevenueService();
//        inventoryService.subscribeToOderService(orderItemFlux);
//        revenueService.subscribeToOderService(orderItemFlux);
//
//        revenueService.reviewStream().subscribe(Util.subscriber("Revenue-Steam"));
//
//        Util.sleepSeconds(15);

        // v2 verson

        OrderService orderService = new OrderService();
        Flux<OrderItem> orderItemFlux = orderService.orderStream();
        OrderProcessor inventoryServiceV2= new InventoryServiceV2();
        OrderProcessor  revenueServiceV2= new RevenueServiceV2();
        orderItemFlux.subscribe(inventoryServiceV2::consume);
        orderItemFlux.subscribe(revenueServiceV2::consume);
        inventoryServiceV2.stream().subscribe(Util.subscriber("inventory"));

        revenueServiceV2.stream().subscribe(Util.subscriber("revenue"));
        Util.sleepSeconds(25);

    }
}

class OrderService {

    public Flux<OrderItem> orderStream() {
        OrderHttpClient httpClient = new OrderHttpClient();
        return httpClient.getOrders().publish().refCount(2);

    }


}

class InventoryService {
    private static Logger logger = LoggerFactory.getLogger(InventoryService.class);
    private Map<String, Integer> map = new HashMap<>();

    public void subscribeToOderService(Flux<OrderItem> orderflux) {

        // do whatever want to do
        orderflux
                .map(x -> {
                    map.putIfAbsent(x.category(), map.getOrDefault(x.category(), 500) - x.quantity());
                    return x;
                })
                .map(x -> "Remaiming Inventory quantity for categeroy : " + map.get(x.category()))
                .subscribe(Util.subscriber("inventory-Service"));
    }

}
class RevenueService {
    private ConcurrentMap<String, Double> revinewMap = new ConcurrentHashMap<>();

    public void subscribeToOderService(Flux<OrderItem> orderflux) {

        // do whatever want to do
        orderflux.map(x -> {

            revinewMap.putIfAbsent(x.category(), revinewMap.getOrDefault(x.category(), 0.0) + x.price());
            return x;
        }).subscribe(Util.subscriber("Revinue-service"));
    }

    public Flux<Map.Entry<String, Double>> reviewStream() {
        return   Flux.interval(Duration.ofSeconds(2))
                .flatMap(x->Flux.fromStream(revinewMap.entrySet().stream()));

    }
}

interface OrderProcessor{
  void consume (OrderItem orderItem);
  Flux<String> stream();
}
class RevenueServiceV2 implements OrderProcessor{
    private ConcurrentMap<String, Double> revinewMap = new ConcurrentHashMap<>();

    @Override
    public void consume(OrderItem orderItem) {
        revinewMap.putIfAbsent(orderItem.category(), revinewMap.getOrDefault(orderItem.category(), 0.0) + orderItem.price());
    }

    @Override
    public Flux<String> stream() {
        return   Flux.interval(Duration.ofSeconds(2))
                .map(x->revinewMap.toString());

    }
}
class InventoryServiceV2 implements OrderProcessor{
    private static Logger logger = LoggerFactory.getLogger(InventoryService.class);
    private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    @Override
    public void consume(OrderItem orderItem) {
        map.putIfAbsent(orderItem.category(), map.getOrDefault(orderItem.category(), 500) - orderItem.quantity());

    }

    @Override
    public Flux<String> stream() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(x->map.toString());
    }
}


class OrderHttpClient extends AbstractHttpClient {

    public Flux<OrderItem> getOrders() {
        return this.httpClient.get().uri("http://localhost:7070/demo04/orders/stream")
                .responseContent()
                .asString()
                .map(OrderHttpClient::MapToOrderItem)
                .doOnNext(x-> System.out.println(x));
    }

    private static OrderItem MapToOrderItem(String x) {
        String[] arr = x.split(":");
        return new OrderItem(arr[0], arr[1], Double.parseDouble(arr[2]), Integer.parseInt(arr[3]))
                ;
    }
}

record OrderItem(String name, String category, double price, int quantity) {
}
