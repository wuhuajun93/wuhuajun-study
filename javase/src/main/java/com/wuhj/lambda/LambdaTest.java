package com.wuhj.lambda;

import org.junit.Test;

import java.util.Comparator;

/**
 * @author wuhj
 * @date 2020/6/23 15:47
 */
public class LambdaTest {
    
    @Test
    public void test(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        };
        runnable.run();
        System.out.println("************************");
    }
    
    
    @Test
    public void test2(){
        Runnable runnable = ()->System.out.println("hello world");
        runnable.run();
        System.out.println("************************");
    }
    
    @Test
    public void test3(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        
        int com1 = com.compare(12,13);
        System.out.println(com1);
    
        Comparator<Integer> com2 = (o1,o2) ->Integer.compare(o1,o2);
        int compare2 = com2.compare(12,13);
        System.out.println(compare2);
    
        Comparator<Integer> com3 = Integer::compare;
        int compare3 = com3.compare(12,13);
        System.out.println(compare3);
    }
    

}
