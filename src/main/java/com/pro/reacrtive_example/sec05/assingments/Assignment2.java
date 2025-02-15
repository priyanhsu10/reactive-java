package com.pro.reacrtive_example.sec05.assingments;

import com.pro.reacrtive_example.common.ExternalServiceClient;
import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Assignment2 {
  private  static   ExternalServiceClient http = new ExternalServiceClient();
    public static void main(String[] args) {

        ProductHttpClient http = new ProductHttpClient();
        Flux.range(1, 4)
                .flatMap(x ->http.getProductName(x))
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);

    }
}
