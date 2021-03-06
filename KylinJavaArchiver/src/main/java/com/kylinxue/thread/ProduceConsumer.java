package com.kylinxue.thread;

import java.util.LinkedList;

public class ProduceConsumer {
    public static void main(String[] args) {
        HoneyPool pool = new HoneyPool();
        Bee p1 = new Bee("p1",pool);
        p1.setName("p1");
        Bee p2 = new Bee("p2",pool);
        p2.setName("p2");
        Bear c1 = new Bear("c1",pool);
        c1.setName("c1");

        p1.start();
        p2.start();
        c1.start();

    }
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
        String name = Thread.currentThread().getName();
        while(pool.size() >= MAX){
            try {
               System.out.println(name +" wait");
               wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
        }
        pool.add(i);
        notify();
        System.out.println(name + " notify");

        return i ;
    }

    public synchronized int remove(){
        String name = Thread.currentThread().getName();
        while(pool.isEmpty()){
            try {
                System.out.println(name + " wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int n = pool.removeFirst();
        notify();
        System.out.println(name + " notify");
        return n;
    }
}

class Producer extends Thread {
    private static int index = 0;
    private String name;
    private HoneyPool pool;

    public Producer(String name, HoneyPool pool) {
        this.name = name;
        this.pool = pool;
    }

    public void run(){
        while(true){
            int n = pool.add(index++);
            System.out.println(name + " add: " + n);
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class Consumer extends Thread {
    private String name;
    private HoneyPool pool;

    public Consumer(String name, HoneyPool pool) {
        this.name = name;
        this.pool = pool;
    }

    public void run(){
        while (true){
            int i = pool.remove();
            System.out.println(name + " remove: " + i);
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


