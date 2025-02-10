package com.pro.reacrtive_example.sec02;

import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec07MonoFromRunnable {

    private  static  final Logger log= LoggerFactory.getLogger(Lec07MonoFromRunnable.class);
    public static void main(String[] args) {

        getProduct(1).subscribe(Util.subscriber());
    }
    private static Mono<String > getProduct(int productId){
        if(productId==1){
            return  Mono.fromSupplier(()->"product 1");
        }
        //runable return lazy empty mono
        return  Mono.fromRunnable(()->notifyBusiness(productId) );
    }
    public  static  void notifyBusiness( int productid){
        log.info("notifying bussiness on unavailable  of product {}",productid);
    }
}
