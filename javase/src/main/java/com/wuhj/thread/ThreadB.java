package com.wuhj.thread;

/**
 * @author wuhj
 * @date 2020/9/22 9:42
 */
public class ThreadB extends Thread {
    
    private Object lock;
    
    public ThreadB(Object lock) {
        super();
        this.lock = lock;
    }
    
    @Override
    public void run() {
        Service service = new Service();
        service.testMethod(lock);
    }
}
