package com.pro.reacrtive_example.sec05.assingments;

import com.pro.reacrtive_example.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ProductHttpClient extends AbstractHttpClient {


    public Mono<String> getProductName(int productId){
        var defalutPath="/demo03/product/"+productId;
        var timeoutPath="/demo03/timeout-fallback/product/"+productId;

        var emptyPath="/demo03/empty-fallback/product/"+productId;
        return  getProductName(defalutPath)
                .timeout(Duration.ofSeconds(2),getProductName(timeoutPath))
                .switchIfEmpty(getProductName(emptyPath));

    }
    public Mono<String> getProductName(String path){
        return  this.httpClient.get().uri(path)
                .responseContent()
                .asString()
                .next();
    }


}
