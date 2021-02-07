package com.wuhj.single;

/**
 * @author wuhj
 * @date 2021/2/7 15:12
 *
 * 饿汉式：
 *      在类初始化时直接创建实例对象,不管是否需要都会创建对象
 *
 * (1)、构造私有化
 * (2)、自行创建，并且用静态变量保存
 * (3)、向外提供这个实例
 * (4)、强调这是一个单例，我们可以用final修饰
 */
public class Singleton1 {
    
    public static final Singleton1 INSTANCE = new Singleton1();
    
    private Singleton1(){}
}
