package com.wuhj.thread;

/**
 * @author wuhj
 * @date 2020/9/16 14:44
 */
public class Singleton {
    //懒汉式
    private Singleton(){
    
    }
    private static Singleton singleton = null;
    
    public static Singleton getInstance(){
        if (singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
