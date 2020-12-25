package com.wuhj.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程创建测试
 *
 * @author wuhj
 * @date 2020/9/16 15:31
 */
public class ThreadTest {
    
    public static void main(String[] args) {
        ThreadCreate threadCreate = new ThreadCreate();
        threadCreate.start();
        
        ThreadCreate02 threadCreate02 = new ThreadCreate02();
        Thread thread = new Thread(threadCreate02);
        thread.start();
    
        ThreadCreate03 threadCreate03 = new ThreadCreate03();
        FutureTask futureTask = new FutureTask(threadCreate03);
        new Thread(futureTask).start();
        
        try {
            Object sum = futureTask.get();
            System.out.println("总和为：" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    
    }
    
    
    
}
