package com.pro.reacrtive_example.sec08;

import com.pro.reacrtive_example.common.Util;
import com.pro.reacrtive_example.sec08.helper.Kayak;

public class Lec06MerUseCase {
    public static void main(String[] args) {
        Kayak.getFlights().subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }
}
