package com.mmall.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class TestSynchronized {

    // 修饰代码快
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} - {}", j, i);
            }
        }
    }

    public synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {}", i);
        }
    }

    public synchronized static void test3(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test3 {} - {}", j, i);
        }
    }

    public void test4(int j) {
        synchronized (TestSynchronized.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test4 {} - {}", j, i);
            }
        }
    }


    public static void main(String[] args) {

        final TestSynchronized test = new TestSynchronized();



        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            test.test1(1);
        });
        executorService.execute(() -> {
            test.test1(2);
        });
        executorService.shutdown();
    }

}


