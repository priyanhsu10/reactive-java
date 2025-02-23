package com.pro.reacrtive_example.sec08.assignment;

import com.pro.reacrtive_example.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ProductHttpClient extends AbstractHttpClient {
private static final String baseUrl="http://localhost:7070";
    public   Mono<Product> getProduct(int id){
       var product= baseUrl +"/demo05/product/"+id;
        var price= baseUrl +"/demo05/price/"+id;
        var review= baseUrl +"/demo05/review/"+id;
       return Mono.zip(get(product),get(price),get(review))
                .map(x->new Product(x.getT1(),x.getT2(),x.getT3()));

    }

    public Mono<String> get(String url) {
        return this.httpClient.get().uri(url)
                .responseContent()
                .asString()
                .next();

    }


}

