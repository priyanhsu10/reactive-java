package com.pro.reacrtive_example.sec10;

public class ClientError extends RuntimeException {
    public ClientError(){
        super("bad request ");
    }
}


