package com.pro.reacrtive_example.sec10;

import com.pro.reacrtive_example.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClientResponse;

public class ExternalProductClient extends AbstractHttpClient {
    public Mono<String> getProductName(int prductId){
       return  get("/demo06/product/"+prductId);
    }
    public Mono<String> getCountry(){
        return  get("/demo06/country");
    }
    private Mono<String> get(String path){
        return  this.httpClient.get().uri(path)
                .response(this::toResponse)
                .next();
    }
    private Flux<String> toResponse(HttpClientResponse httpClientResponse, ByteBufFlux byteBufFlux){
        return  switch (httpClientResponse.status().code()){
            case 200 -> byteBufFlux.asString();
            case 400->Flux.error(new ClientError());
            default -> Flux.error(new ServerError());

        };
    }
}
