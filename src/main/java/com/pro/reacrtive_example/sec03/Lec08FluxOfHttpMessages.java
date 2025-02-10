package com.pro.reacrtive_example.sec03;

import com.pro.reacrtive_example.common.ExternalServiceClient;
import com.pro.reacrtive_example.common.Util;

public class Lec08FluxOfHttpMessages {

    public static void main(String[] args) {
        var http=new ExternalServiceClient();
        http.getProducts()
                .subscribe(Util.subscriber("sub1"));
        http.getProducts()
                .subscribe(Util.subscriber("sub2"));
        Util.sleepSeconds(6);
    }
}
