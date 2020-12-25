package com.wuhj.juc;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    
    private Lock lock = new ReentrantLock();
    private int number = 30;
    
    public void sale(){
        lock.lock();
        //判断
        try {
            if(number > 0){
                System.out.println(Thread.currentThread().getName() + "\t 卖了第：" + (number--) + "张票");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    
    }
    
}


/**
 * @author wuhj
 * @date 2020/11/24 8:39
 */
public class SaleTicketDemo {
    
    public static void main(String[] args) throws Exception{
        
        Ticket ticket = new Ticket();
        
        new Thread(() -> { for (int i = 1; i <= 40; i++) ticket.sale();},"A").start();
        new Thread(() -> { for (int i = 1; i <= 40; i++) ticket.sale();},"B").start();
        new Thread(() -> { for (int i = 1; i <= 40; i++) ticket.sale();},"C").start();
        new Thread(() -> { for (int i = 1; i <= 40; i++) ticket.sale();},"D").start();
        
        
        
    }
    
}
