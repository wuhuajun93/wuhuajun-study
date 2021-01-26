package com.wuhj.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AQSDemo {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        //带入一个银行办理业务的案列来模拟我们的AQS如何进行线程的管理和通知唤醒机制

        //3个线程模拟3个来银行网点办理业务的顾客

        //A顾客就是第一个顾客，此时受理窗口没有任何人，A可以直接去办理
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("-------A thread come in");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }

        },"A").start();

        //第2个顾客，第2个线程---->由于受理业务窗口只有1个(只能一个线程持有锁)，此时B只能等待
        //进入候客区
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("-------A thread come in");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }

        },"B").start();

        //第3个顾客，第3个线程---->由于受理业务窗口只有1个(只能一个线程持有锁)，此时B只能等待
        //进入候客区
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("-------A thread come in");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }

        },"C").start();
    }
}
