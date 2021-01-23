package com.wuhj.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 手写自旋锁
 */
public class SpinLockDemo {
    
    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in O(n_n)O");
        //如果没有线程则初始化一个
        while (!atomicReference.compareAndSet(null, thread)){
        
        }
    }
    
    public void myUnlock(){
        Thread thread = Thread.currentThread();
        //如果有一个线程则清空
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoked myUnlock()");
    }
    
    public static void main(String[] args) {
        
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        
        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
                spinLockDemo.myUnlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        },"AA").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.myUnlock();
        },"BB").start();
        
    }
    
}
