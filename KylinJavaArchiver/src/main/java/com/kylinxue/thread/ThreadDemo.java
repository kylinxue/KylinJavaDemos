package com.kylinxue.thread;


public class ThreadDemo {

	public static void main(String[] args) {
		new MyThread("T1").start();
		new MyThread("T2").start();

	}

}

class MyThread extends Thread{
	private String name ;

	public MyThread(String name) {
		this.name = name;
	}

	public void run() {
		for(int i = 0; i <= 100 ; i ++){
			System.out.println(name + " : " + i);
			yield();	// 放弃本次抢占cpu，以使得线程之间尽量均衡
		}
	}
}