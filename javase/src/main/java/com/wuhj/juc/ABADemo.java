package com.wuhj.juc;

import java.util.concurrent.atomic.AtomicReference;

public class ABADemo {
    
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    
    
    
    public static void main(String[] args) {
        
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
            
        },"t1").start();
        
    }
}
