package com.wuhj.thread;

/**
 * @author wuhj
 * @date 2020/9/16 11:59
 */
public class ThreadCreate extends Thread{
    
    @Override
    public void run() {
        System.out.println("继承Thread类创建线程");
    }
    
    
}


