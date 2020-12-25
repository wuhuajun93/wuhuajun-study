package com.wuhj.juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirConditioner{
    
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    
    public void increment() throws InterruptedException{
        lock.lock();
        try {
            //判断
            while (number != 0){
                condition.await();//this.wait()
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知
            condition.signalAll();//this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    
    public void decrement() throws InterruptedException{
        lock.lock();
        try {
            //判断
            while (number == 0){
                condition.await();//this.wait()
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知
            condition.signalAll();//this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


//    public synchronized void increment() throws InterruptedException{
//        //判断
//        while (number != 0){
//            this.wait();
//        }
//        //干活
//        number++;
//        System.out.println(Thread.currentThread().getName() + "\t" + number);
//        //通知
//        this.notifyAll();
//    }
//
//    public synchronized void decrement() throws InterruptedException{
//        //判断
//        while (number == 0){
//            this.wait();
//        }
//        //干活
//        number--;
//        System.out.println(Thread.currentThread().getName() + "\t" + number);
//        //通知
//        this.notifyAll();
//    }
}

/**
 * 题目：现在两个线程，可以操作初始值为0的一个变量
 * 实现一个线程对该变量加1，一个线程对该变量减1
 * 交替实现，来10轮，变量初始值为0
 *
 * 1.高聚低合前提下，线程操作资源类
 * 2.判断/干活/通知
 * 3.多线程交护中，必须要防止多线程的虚假唤醒，也即(判断只用while，不能用if)
 */
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        
        AirConditioner airConditioner = new AirConditioner();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
        
    }
    
}
