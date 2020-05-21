package com.mmall.concurrency.example.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnSafeDemo {

	public static void main(String[] args) throws Exception {
		// 通过反射得到theUnsafe对应的Field对象
		Field field = Unsafe.class.getDeclaredField("theUnsafe");
//		field.setAccessible(true);
//		Unsafe unsafe = (Unsafe) field.get(null);
//		System.out.println(unsafe);
//		// 通过allocateInstance直接创建对象
//		User user = (User) unsafe.allocateInstance(User.class);
//		Class<? extends User> clazz = user.getClass();
//
//		Field name = clazz.getDeclaredField("name");
//		Field age = clazz.getDeclaredField("age");
//		Field id = clazz.getDeclaredField("id");
//		Field val = clazz.getDeclaredField("val");
//
//		// 获取实例变量name和age在对象内存中的偏移量并设置值
//		unsafe.putInt(user, unsafe.objectFieldOffset(age), 18);
//		unsafe.putObject(user, unsafe.objectFieldOffset(name), "android TV");
//		// 这里返回 User.class，
//		Object staticBase = unsafe.staticFieldBase(id);
//		System.out.println("staticBase:" + staticBase);
//
//		long staticOffset = unsafe.staticFieldOffset(id);
//		System.out.println("设置前的ID:" + unsafe.getObject(staticBase, staticOffset));
//		// 设置值
//		unsafe.putObject(staticBase, staticOffset, "SSSSSS");
//
//		System.out.println("设置后的ID:" + unsafe.getObject(staticBase, staticOffset));
//
//		System.out.println("User:" + user.toString());
//
//		long data = 1000;
//		byte size = 1;// 单位字节
//		// 调用allocateMemory分配内存,并获取内存地址memoryAddress
//		long memoryAddress = unsafe.allocateMemory(size);
//		// 直接往内存写入数据
//		unsafe.putAddress(memoryAddress, data);
//		// 获取指定内存地址的数据
//		long addrData = unsafe.getAddress(memoryAddress);
//		System.out.println("addrData:" + addrData);

	}
}
