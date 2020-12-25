package com.wuhj.thread;

/**
 * @author wuhj
 * @date 2020/9/21 11:23
 */
public class MyThreadRun {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("运行结束");
    }
}
