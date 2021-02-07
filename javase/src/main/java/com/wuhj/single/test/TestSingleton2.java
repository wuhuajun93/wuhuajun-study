package com.wuhj.single.test;

import com.wuhj.single.Singleton2;

/**
 * @author wuhj
 * @date 2021/2/7 15:21
 */
public class TestSingleton2 {
    public static void main(String[] args) {
    
        Singleton2 instance = Singleton2.INSTANCE;
        System.out.println(instance);
    }
}
