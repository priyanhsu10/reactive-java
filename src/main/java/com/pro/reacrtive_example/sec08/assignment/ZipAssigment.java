package com.pro.reacrtive_example.sec08.assignment;

import com.pro.reacrtive_example.common.Util;

public class ZipAssigment {

    public static void main(String[] args) {
        ProductHttpClient httpClient= new ProductHttpClient();

        for(int i= 0;i<10;i++){
            httpClient.getProduct(i)
                    .subscribe(Util.subscriber());

        }
        Util.sleepSeconds(10);
    }
}

