package com.wuhj.thread;

/**
 * @author wuhj
 * @date 2020/9/16 15:35
 */
public class ThreadCreate02 implements Runnable {
    
    @Override
    public void run() {
        System.out.println("实现Runnable接口创建线程");
    }
}
