package com.mmall.concurrency.example.locks;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

	private static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {

		new Thread() {
			public void run() {
				try {
					toDo();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();

		new Thread() {
			public void run() {
				try {
					toDo();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

	public static void toDo() throws InterruptedException {
		lock.lock();
		System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
		Thread.sleep(3000);
		System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
		lock.unlock();
	}
}
