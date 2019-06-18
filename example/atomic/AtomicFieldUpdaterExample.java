package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicFieldUpdaterExample {

    private static AtomicIntegerFieldUpdater<AtomicFieldUpdaterExample> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicFieldUpdaterExample.class, "count");
    // 必须volatile修饰，非static
    public volatile int count = 100;

    public int getCount() {
        return count;
    }

    private static AtomicFieldUpdaterExample example = new AtomicFieldUpdaterExample();

    public static void main(String[] args) {
        if (updater.compareAndSet(example, 100, 120)) {
            System.out.println("update success 1 = " + example.getCount());

        }

        if (updater.compareAndSet(example, 100, 120)) {
            System.out.println("update success 2 = " + example.getCount());
        } else {
            System.out.println("update failed = " + example.getCount());
        }
    }

}
