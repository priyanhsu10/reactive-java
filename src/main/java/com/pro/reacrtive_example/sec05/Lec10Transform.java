package com.pro.reacrtive_example.sec05;

import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Lec10Transform {
    private static  final Logger log= LoggerFactory.getLogger(Lec10Transform.class);
    record Customer(int id, String name){}
    record PurchaseOrder(String productName, int price,int quantity){}

    public static void main(String[] args) {
        var isDebug=false;
        getCustomers()
//                .doOnNext(x->log.info("reciecd : {}",x))
//                .doOnComplete(()->log.info("complited"))
//                .doOnError(err->log.error("error",err))
                .transform(isDebug?addDebugger():Function.identity())
                .subscribe();
        getPurchaseOrder()
//                .doOnNext(x->log.info("reciecd : {}",x))
//                .doOnComplete(()->log.info("complited"))
//                .doOnError(err->log.error("error",err))
                .transform(isDebug?addDebugger():Function.identity())
                .subscribe();

    }
    private  static Flux<Customer> getCustomers(){
       return Flux.range(1,3)
                .map(x->new Customer(x, Util.faker.name().name()));
    }
    private  static Flux<PurchaseOrder> getPurchaseOrder(){
        return Flux.range(1,5)
                .map(x->new PurchaseOrder(Util.faker.commerce().productName(),x,x*10));
    }
    private  static <T> UnaryOperator<Flux<T>> addDebugger(){
        return  flux->flux
                .doOnNext(x->log.info("reciecd : {}",x))
                .doOnComplete(()->log.info("complited"))
                .doOnError(err->log.error("error",err));
    }
}
