package com.pro.reacrtive_example.sec04;

import com.pro.reacrtive_example.common.Util;
import com.pro.reacrtive_example.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

public class Lec03FluxSinkThreadSafty {
    public static void main(String[] args) {
//        demo1();
        demo2();
    }
    public static void  demo1(){
        ArrayList<Integer> list = new ArrayList<>();

        Runnable r= ()->{
            for(int i =0;i<10000;i++){
                list.add(i);
            }
        };

        Flux.range(1,10).subscribe(x->new Thread(r).start());

        Util.sleepSeconds(3);
        System.out.println("list size :"+list.size());

    }
    public  static  void demo2(){
        ArrayList<String> list = new ArrayList<>();
        var generator = new NameGenerator();
        Flux.create(generator)
                .subscribe(list::add);


        Runnable r= ()->{
            for(int i =0;i<10000;i++){
                generator.generate();
            }
        };

        Flux.range(1,10).subscribe(x->new Thread(r).start());

        Util.sleepSeconds(3);
        System.out.println("list size :"+list.size());
    }
}
