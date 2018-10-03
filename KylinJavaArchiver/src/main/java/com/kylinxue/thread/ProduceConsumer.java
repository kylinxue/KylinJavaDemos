package com.kylinxue.thread;

import java.util.LinkedList;

public class ProduceConsumer {

}

/**
 *  票池，生产者生产的东西放入此地
 */
class Pool {

    private LinkedList<Integer> pool = new LinkedList<Integer>();
    private int MAX = 100;


    public synchronized int add(Integer i){
        // 当池中物品达到最大值时，池对象将执行此代码的线程放入它对应的等待队列中
        // 该线程等待，不再执行
        // wait方法和add方法之间可能会发生很多事情，所以while一般和wait（）配合使用
       while(pool.size() >= MAX){
           try {
               wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
        pool.add(i);
        return i ;
    }

    public synchronized int remove(){
		try {
			while(pool.isEmpty()){
				Thread.sleep(50);
			}
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return pool.removeFirst();
    }
}

class Producer extends Thread {
    private static int index = 0;
    private String name;
    private Pool pool;

    public Producer(String name, Pool pool) {
        this.name = name;
        this.pool = pool;
    }

    public void run(){
        while(true){
            int n = pool.add(index++);
            System.out.println(name + "add: " + n);
        }

    }
}

class Consumer extends Thread {
    private String name;
    private Pool pool;

    public Consumer(String name, Pool pool) {
        this.name = name;
        this.pool = pool;
    }

    public void run(){
        while (true){
            pool.remove();
            System.out.println();
        }
    }
}


