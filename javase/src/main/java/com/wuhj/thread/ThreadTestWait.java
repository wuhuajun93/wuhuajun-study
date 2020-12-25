package com.wuhj.thread;

/**
 * @author wuhj
 * @date 2020/9/22 9:47
 */
public class ThreadTestWait {
    
    public static void main(String[] args) {
        Object lock = new Object();
        ThreadA threadA = new ThreadA(lock);
        threadA.setName("A");
        threadA.start();
        
        ThreadB threadB = new ThreadB(lock);
        threadB.setName("B");
        threadB.start();
    }
    
}
