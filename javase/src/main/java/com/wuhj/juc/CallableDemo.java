package com.wuhj.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


class MyThread implements Callable<Integer>{
    
    @Override
    public Integer call() throws Exception {
        System.out.println("******come in here");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1024;
    }
}

/**
 * @author wuhj
 * @date 2020/11/23 9:38
 *
 * 多线程中，第三种获得多线程的方式
 *
 */
public class CallableDemo {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
    
        FutureTask futureTask = new FutureTask(new MyThread());
        
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();
        
        System.out.println(Thread.currentThread().getName() + "******结算完成");
        System.out.println(futureTask.get());
    }
}
