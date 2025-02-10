package com.pro.reacrtive_example.sec02;

import com.pro.reacrtive_example.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getProduct(int productId){

      return   this.httpClient.get().uri("/demo01/product/"+productId)
                .responseContent()
                .asString()
                .next();
    }


}
