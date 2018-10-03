package com.kylinxue.thread;

/**
 * 通过 synchronized 练习锁，线程安全。
 */
public class SaleTickets {
    public static void main(String[] args) {
        TicketsPool pool = new TicketsPool();
        Saler s1 = new Saler("S1", pool);
        Saler s2 = new Saler("S2", pool);

        s1.start();
        s2.start();
    }
}

class TicketsPool{
    private int ticketNum = 100;

    // 返回值表示取到几号票
    // 0号票表示没有票了，票号：1 - 100 号
    public synchronized  int getTicket(){
        if(ticketNum==0){
            return 0;
        }
        return ticketNum--;
    }
    // 返回值表示取完票还剩多少张
    public synchronized int remainAfterGetting(){
        if (ticketNum==0){
            return 0;
        }
        return --ticketNum;
    }
}
class Saler extends Thread{
    private String name;
    private TicketsPool pool;

    public Saler(String name, TicketsPool pool) {
        this.name = name;
        this.pool = pool;
    }

    public void run(){
        while (true){
            int ticketNum = pool.getTicket();
            if(ticketNum == 0){
                System.out.println("no tickets");
                return;
            }

            System.out.println(name + ":"+ ticketNum);
        }
    }
}
