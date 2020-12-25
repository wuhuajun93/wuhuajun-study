package com.wuhj.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wuhj
 * @date 2020/9/16 15:57
 */
public class ThreadPool {
    
    public static void main(String[] args) {
    
        ExecutorService service = Executors.newFixedThreadPool(10);
        //Runnable
        //service.execute(new NumberThread());
        //Callab
        service.submit(new NumberThread02());
        
        service.shutdown();
    }



}

class NumberThread implements Runnable{
    
    @Override
    public void run() {
        for(int i = 0; i <= 100; i++) {
          if(i % 2 == 0){
              System.out.println(Thread.currentThread().getName() + ":" +i);
          }
        }
    }
}

class NumberThread02 implements Callable{
    
    @Override
    public Object call() throws Exception {
        System.out.println("Callable接口实现");
        for(int i = 0; i <= 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" +i);
            }
        }
        return null;
    }
}
