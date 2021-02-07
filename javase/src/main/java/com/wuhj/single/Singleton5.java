package com.wuhj.single;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhj
 * @date 2021/2/7 15:33
 * 懒汉式-延迟创建这个实例对象     适合多线程线程
 *
 * (1)、构造私有化
 * (2)、用一个静态变量保存这个唯一的实例
 * (3)、提供一个公共的静态方法，获取这个实例对象
 * (4)、
 */
public class Singleton5 {
    private Singleton5(){}
    
    private static Singleton5 instance = null;
    
    public static Singleton5 getInstance(){
        synchronized (Singleton5.class){
            if(instance == null){
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance = new Singleton5();
            }
        }
        return instance;
    }
}
