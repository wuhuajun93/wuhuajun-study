package com.wuhj.single.test;

import com.wuhj.single.Singleton1;

/**
 * @author wuhj
 * @date 2021/2/7 15:19
 */
public class TestSingleton1 {
    
    public static void main(String[] args) {
        Singleton1 instance = Singleton1.INSTANCE;
        System.out.println(instance);
    }
}
