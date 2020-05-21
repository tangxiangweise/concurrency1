package com.mmall.concurrency;

public class ArrayQueue<T> {

    /**
     * 队列数量
     */
    private int count;
    /**
     * 数据存储
     */
    private Object[] items;
    /**
     * 队列满时的阻塞锁
     */
    private Object full = new Object();
    /**
     * 队列空时的阻塞锁
     */
    private Object empty = new Object();
    /**
     * 写入数据时的下标
     */
    private int putIndex;
    /**
     * 获取数据时的下标
     */
    private int getIndex;

    public ArrayQueue(int size) {
        items = new Object[size];
    }

    public void put(T t) {
        synchronized (full) {
            while (count == items.length) {
                try {
                    full.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        synchronized (empty) {
            // 写入
            items[putIndex] = t;
            count++;

            putIndex++;
            if (putIndex == items.length) {
                // 超过数组长度后需要从头开始
                putIndex = 0;
            }
            empty.notify();
        }
    }

    public T get() {
        synchronized (empty) {
            while (count == 0) {
                try {
                    empty.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
        }
        synchronized (full) {
            Object result = items[getIndex];
            items[getIndex] = null;
            count--;
            getIndex++;
            if (getIndex == items.length) {
                getIndex = 0;
            }
            full.notify();
            return (T) result;
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayQueue<String> queue = new ArrayQueue<>(3);
        new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.println("queue get value=" + queue.get());
            }
        }.start();

        Thread.sleep(1000);

        System.out.println("queue put data");
        queue.put("1");
        queue.put("2");
        queue.put("3");
        queue.put("4");
        System.out.println("queue put all");
        queue.put("5");
        System.out.println("queue put all null");
    }


}
