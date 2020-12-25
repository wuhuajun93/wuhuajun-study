package com.wuhj.thread;

import java.util.concurrent.Callable;

/**
 * @author wuhj
 * @date 2020/9/16 15:39
 */
public class ThreadCreate03 implements Callable {
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for(int i = 0; i < 100; i++) {
          if(i % 2 == 0){
              System.out.println(i);
              sum += i;
          }
        }
        return sum;
    }
}
