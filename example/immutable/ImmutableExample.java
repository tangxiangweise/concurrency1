package com.mmall.concurrency.example.immutable;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

public class ImmutableExample {

    private final static Integer a = 1;

    private final static String b = "2";

    private static Map<Integer, Integer> map = Maps.newHashMap();


    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {

    }


}
