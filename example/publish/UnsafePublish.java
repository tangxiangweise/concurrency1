package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annoations.NotThreadSafe;

import java.util.Arrays;

@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"1", "b"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        System.out.println(Arrays.toString(unsafePublish.getStates()));
        unsafePublish.getStates()[0] = "a";
        System.out.println(Arrays.toString(unsafePublish.getStates()));
    }
}
