package com.pro.reacrtive_example.sec02;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {
    public static void main(String[] args) {

//        getUserName(3).subscribe(Util.subscriber());

     getUserName(3).subscribe(x-> System.out.println(x),
     error->{
         System.out.println(error);
     });
    }
    public  static Mono<String> getUserName(int userid){

        return  switch (userid){
            case  1 ->Mono.just("sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }
}
