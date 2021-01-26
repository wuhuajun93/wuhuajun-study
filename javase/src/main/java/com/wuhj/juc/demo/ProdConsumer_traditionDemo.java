package com.wuhj.juc.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            //判断
            while (number != 0){
                //等待，不能生产
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
    public void decrement() throws Exception {
        lock.lock();
        try {
            //判断
            while (number == 0){
                //等待，不能消费
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void add(){
        ++number;
    }
}

/**
 * @author wuhj
 * @date 2021/1/23 23:48
 *
 * 题目：一个初始值为零的变量，两个线程对其交替操作，一个加1一个减1，来5轮
 *
 * 1   线程     操作(方法)    资源类
 * 2   判断     干活          通知
 * 3   防止虚假唤醒机制
 *
 */
public class ProdConsumer_traditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

    }
}
