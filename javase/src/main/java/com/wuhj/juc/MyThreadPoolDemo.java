package com.wuhj.juc;

import java.util.concurrent.*;

/**
 * @author wuhj
 * @date 2020/11/23 15:31
 *
 * 线程池7大参数
 *      1.corePoolSize:线程池中的常驻核心线程数
 *      2.maximumPoolSize:线程池中能够容纳同时执行的最大线程数，此值必须大于等于1
 *      3.keepAliveTime:多余的空先线程的存活时间，当前池中线程数量超过corePoolSize时，当前空闲时间达到keepAliveTime时，多余线程会被销毁，直到只剩下corePoolSize个线程为止
 *      4.unit:KeepAliveTime的单位
 *      5.workQueue:任务队列，被提交但尚未被执行的任务
 *      6.threadFactory:表示生成线程池中工作线程的线程工厂，用于创建线程，一般默认的即可
 *      7.handle:拒绝策略，表示当队列满了，并且工作线程大于等于线程池的最大线程数时如何来拒绝请求执行的runnable的策略
 *
 */
public class MyThreadPoolDemo {
    
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    
        try {
            //模拟有10个顾客过来银行办理业务，目前池子里面有5个工作人员提供服务
            for(int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
        
    }
    
    
    
    public static void initPool() {
        //一个线程池有5个工作线程，类似银行有5个受理窗口
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //一个线程池有1个工作线程，类似银行有1个受理窗口
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 一个线程池有N个工作线程，类似银行有N个受理窗口
        ExecutorService threadPool = Executors.newCachedThreadPool();
        
        try {
            //模拟有10个顾客过来银行办理业务，目前池子里面有5个工作人员提供服务
            for(int i = 1; i <= 10; i++) {
              threadPool.execute(() -> {
                  System.out.println(Thread.currentThread().getName() + "\t 办理业务");
              });
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
    
}
