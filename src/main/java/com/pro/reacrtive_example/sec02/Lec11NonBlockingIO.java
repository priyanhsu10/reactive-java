package com.pro.reacrtive_example.sec02;

import com.pro.reacrtive_example.common.ExternalServiceClient;
import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec11NonBlockingIO {
    private static final Logger log = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        for (int i = 1; i <=50; i++) {

            client.getProduct(i)
                    .subscribe(Util.subscriber());

        }
        Util.sleepSeconds(2);
    }
}
