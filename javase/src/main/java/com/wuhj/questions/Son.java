package com.wuhj.questions;

/**
 * @author wuhj
 * @date 2021/2/7 16:27
 *
 * 先初始化父类
 * 初始化子类
 *
 * 子类的初始化<clinit>:
 * (1) j = method()       5  1
 * (1) 子类的静态代码块    10 6
 *
 * 子类的实例化方法<init>：
 * (1) super()            9  3  2
 * (2) i = test();        9
 * (3) 子类的非静态代码块  8
 * (4) 子类的无参构造(最后) 7
 *
 *  因为创建了两个Son对象，因此实例化方法<init>执行两次
 *
 *  9  3  2  9  8  7
 */
public class Son extends Father{
    private int i = test();
    private static int j = method();
    
    static {
        System.out.print("\t6");
    }
    
    Son() {
        //super();//写或者不写都在，在子类构造器中一定会调用父类的构造器
        System.out.print("\t7");
    }
    
    {
        System.out.print("\t8");
    }
    
    @Override
    public int test() {
        System.out.print("\t9");
        return 1;
    }
    
    public static int method() {
        System.out.print("\t10");
        return 1;
    }
    
    public static void main(String[] args) {
        Son s1 = new Son();
        System.out.println();
        Son s2 = new Son();
    }
}
