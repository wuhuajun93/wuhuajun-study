package com.wuhj.thread;


import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{
    
    private String lockA;
    private String lockB;
    
    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }
    
    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + lockA + "\t 尝试获得：" + lockB);
    
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + lockB + "\t 尝试获得：" + lockA);
            }
        }
    }
}

/**
 * @author wuhj
 * @date 2021/1/20 17:19
 *
 * 死锁是两个或以上的进程在执行过程中因资源争夺而造成的一种互相等待现象，若无外力干涉那他们都将无法推进下去
 */
public class DeadLockDemo {
    
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
    
        new Thread(new HoldLockThread(lockA, lockB), "threadAAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "threadBBB").start();
        
        /**
         *
         * linux  ps -ef | grep xxx
         * windows下的Java运行程序，也有类似的查看进程命令。但是目前我们需要查看的只是java
         *       jps = java ps   jps -l
         *
         */
    }
}
