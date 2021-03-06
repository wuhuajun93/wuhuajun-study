package com.wuhj.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author wuhj
 * @date 2020/11/23 11:05
 *
 * 争车位
 *
 */
public class SemaphoreDemo {
    
    public static void main(String[] args) {
        //模拟资源类，三个空车位
        Semaphore semaphore = new Semaphore(3);
        for(int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢占到了车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
        
    }
    
}
