package com.it18zhang.archive.test;

import org.junit.Test;

public class TestThread {

	/**
	 *
	 */
	@Test
	public void test1(){
		new MyThread("T1").start();
		new MyThread("T2").start();
	}

	class MyThread extends Thread{
		private String name ;

		public MyThread(String name) {
			this.name = name;
		}

		public void run() {
			for(int i = 0; i <= 100 ; i ++){
				System.out.println(name + " : " + i);
				yield();
			}
		}
	}
}
