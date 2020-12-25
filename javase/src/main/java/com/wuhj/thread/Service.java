package com.wuhj.thread;

/**
 * @author wuhj
 * @date 2020/9/22 9:44
 */
public class Service {
    
    public void testMethod(Object lock){
        try {
            synchronized (lock){
                System.out.println(Thread.currentThread().getName()+ " begin wait()");
                lock.wait();
                //Thread.sleep(40000);
                System.out.println(Thread.currentThread().getName()+ " end wait()");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
