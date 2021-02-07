package com.wuhj.single;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhj
 * @date 2021/2/7 15:33
 * 懒汉式-延迟创建这个实例对象     适合单线程
 *
 * (1)、构造私有化
 * (2)、用一个静态变量保存这个唯一的实例
 * (3)、提供一个公共的静态方法，获取这个实例对象
 * (4)、
 */
public class Singleton4 {
    private Singleton4(){}
    
    private static Singleton4 instance = null;
    
    public static Singleton4 getInstance(){
        if(instance == null){
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Singleton4();
        }
        return instance;
    }
}
