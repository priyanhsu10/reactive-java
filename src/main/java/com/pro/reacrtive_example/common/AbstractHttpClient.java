package com.pro.reacrtive_example.common;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

public abstract class AbstractHttpClient {

    protected HttpClient httpClient;
    private  static  String BASE_URL="http://localhost:7070";
    public  AbstractHttpClient(){
        LoopResources resources= LoopResources.create("priyanshu",1,true);
        this.httpClient=HttpClient.create().runOn(resources).baseUrl(BASE_URL);
    }
}
