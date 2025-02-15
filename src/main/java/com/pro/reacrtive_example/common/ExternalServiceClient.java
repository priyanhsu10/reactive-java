package com.pro.reacrtive_example.common;

import com.pro.reacrtive_example.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getProduct(int productId){

      return   this.httpClient.get().uri("/demo01/product/"+productId)
                .responseContent()
                .asString()
                .next();
    }
    public Flux<String>  getProducts(){

        return  this.httpClient.get().uri("/demo02/name/stream")
                .responseContent()
                .asString();
    }

    public Flux<Integer> getStockPrices(){
        return  this.httpClient.get().uri("/demo02/stock/stream")
                .responseContent()
                .asString()
                .map(Integer::parseInt);
    }


}
