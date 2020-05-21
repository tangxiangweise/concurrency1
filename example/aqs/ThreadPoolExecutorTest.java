package com.mmall.concurrency.example.aqs;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
	public static void main(String[] args) {
		ThreadPoolExecutor e = new ThreadPoolExecutor(5, 8, 5L, TimeUnit.SECONDS, null);
	}
}
