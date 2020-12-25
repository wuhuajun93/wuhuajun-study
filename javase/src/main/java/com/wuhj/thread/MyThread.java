package com.wuhj.thread;

/**
 * @author wuhj
 * @date 2020/9/21 11:22
 */
public class MyThread extends Thread {
    
    @Override
    public synchronized void run() {
        System.out.println("MyThread");
    }
}

