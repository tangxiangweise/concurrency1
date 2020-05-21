package com.mmall.concurrency.example.aqs;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

	static AtomicInteger atomicInteger = new AtomicInteger(100);

	static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(200, 0);

	static Thread t1 = new Thread(new Runnable() {
		public void run() {
			// 更新为200
			atomicInteger.compareAndSet(100, 200);
			// 更新为100
			atomicInteger.compareAndSet(200, 100);
		}
	});

	static Thread t2 = new Thread(new Runnable() {
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean flag = atomicInteger.compareAndSet(100, 500);
			System.out.println("flag:" + flag + ",newValue:" + atomicInteger);
		}
	});

	static Thread t3 = new Thread(new Runnable() {
		public void run() {
			int time = atomicStampedReference.getStamp();
			// 更新为200
			atomicStampedReference.compareAndSet(100, 200, time, time + 1);
			// 更新为100
			int time2 = atomicStampedReference.getStamp();
			atomicStampedReference.compareAndSet(200, 100, time2, time2 + 1);
			System.out.println("t3:" + atomicStampedReference.getReference());
		}
	});

	static Thread t4 = new Thread(new Runnable() {
		public void run() {
			int time = atomicStampedReference.getStamp();
			System.out.println("sleep 前 t4 time:" + time);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean flag = atomicStampedReference.compareAndSet(100, 500, time, time + 1);
			System.out.println("flag:" + flag + ",newValue:" + atomicStampedReference.getReference());
		}
	});

	public static void main(String[] args) throws InterruptedException {
		t1.start();
		t2.start();
		t1.join();
		t2.join();

		t3.start();
		t4.start();
		/**
		 * 输出结果: flag:true,newValue:500 sleep 前 t4 time:0
		 * flag:false,newValue:200
		 */
	}
}
