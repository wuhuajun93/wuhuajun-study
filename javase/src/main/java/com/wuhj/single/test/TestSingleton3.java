package com.wuhj.single.test;

import com.wuhj.single.Singleton3;

/**
 * @author wuhj
 * @date 2021/2/7 15:30
 */
public class TestSingleton3 {
    
    public static void main(String[] args) {
        Singleton3 instance = Singleton3.INSTANCE;
        System.out.println(instance);
    }
}
