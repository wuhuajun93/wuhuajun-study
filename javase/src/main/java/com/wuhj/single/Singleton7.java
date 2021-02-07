package com.wuhj.single;

/**
 * @author wuhj
 * @date 2021/2/7 15:50
 * 静态内部类：
 *      在内部类被加载和实例化时，才创建INSTANCE实例对象
 *      静态内部类不会自动随着外部类的加载和初始化而初始化，它是要单独加载和初始化的
 *      因为是在内部类加载和初始化时，创建的，因此是线程安全的
 *
 */
public class Singleton7 {
    
    private Singleton7(){}
    
    private static class Inner{
        private static final Singleton7 INSTANCE = new Singleton7();
    }
    
    public static Singleton7 getInstace(){
        return Inner.INSTANCE;
    }
}
