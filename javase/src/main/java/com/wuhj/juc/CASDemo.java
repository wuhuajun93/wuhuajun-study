package com.wuhj.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wuhj
 * @date 2021/1/21 16:48
 */
public class CASDemo {
    
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
    
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t current value: " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2014) + "\t current value: " + atomicInteger.get());
        
        atomicInteger.getAndIncrement();
    }
    
}
