package com.wuhj.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wuhj
 * @date 2021/1/26 17:22
 */
public class LockSupportDemo {
    
    static Object objectLock = new Object();

    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    
    public static void main(String[] args) {
    
        Thread a = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "-------come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t" + "-------被唤醒");
            }, "A");
        a.start();
        
        Thread b = new Thread(() -> {
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName() + "\t" + "-------通知");
        }, "B");
        b.start();
    }
    
    private static void lockAwaitSignle() {
        new Thread(() -> {
    
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "-------come in");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "-------被唤醒");
            } finally {
                lock.unlock();
            }
    
        }, "A").start();
        
        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t" + "-------通知");
            } finally {
                lock.unlock();
            }
        }, "B").start();
    }
    
    private static void synchronizedWait() {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (objectLock){
                System.out.println(Thread.currentThread().getName() + "\t" + "-------come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "-------被唤醒");
            }
            
        }, "A").start();
        
        new Thread(() -> {
            synchronized (objectLock){
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t" + "-------通知");
                
            }
        
        }, "B").start();
    }
}
