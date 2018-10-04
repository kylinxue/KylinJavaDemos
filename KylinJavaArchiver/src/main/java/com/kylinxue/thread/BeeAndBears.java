package com.kylinxue.thread;

import java.util.LinkedList;
/**蜜蜂 	生产 1份蜂蜜；蜜蜂100只
 熊 		在蜂蜜满20份的时候才会吃蜂蜜；熊2只
 蜜罐
 此时蜜蜂太多，一开始蜂蜜不够，熊肯定会进入到等待队列，蜜蜂很快将蜂蜜增加到最大值，也都相继进入等待队列
 最终熊只有%2的机会被通知到，所以很多时候大家都是等待，效率太低。可以给在remove()中的wait(5),表示熊只等待5毫秒就出来。
 */
public class BeeAndBears {
    public static void main(String[] args) {
        HoneyPool pool = new HoneyPool();
        Bee[] bees = new Bee[100];
        for(int i=0;i<100;++i){
            Bee b = new Bee("bee"+i,pool);
            b.setName("bee"+i);
            bees[i] = b;
        }
        Bear bear0 = new Bear("bear0", pool);
        Bear bear1 = new Bear("bear1", pool);
        bear0.setName("bear0");
        bear1.setName("bear1");

        for(Bee b : bees){
            b.start();
        }
        bear0.start();
        bear1.start();

    }
}

/**
 *  票池，生产者生产的东西放入此地
 */
class HoneyPool {

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
        while(pool.size()<20){
            try {
                System.out.println(name + " wait");
                wait(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<20;++i) pool.removeFirst();

        notify();
        System.out.println(name + " notify");
        return 20;
    }
}

class Bee extends Thread {
    private static int index = 0;
    private String name;
    private HoneyPool pool;

    public Bee(String name, HoneyPool pool) {
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

class Bear extends Thread {
    private String name;
    private HoneyPool pool;

    public Bear(String name, HoneyPool pool) {
        this.name = name;
        this.pool = pool;
    }

    public void run(){
        while (true){
            int i = pool.remove();
            System.out.println(name + " remove: " + i);
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


