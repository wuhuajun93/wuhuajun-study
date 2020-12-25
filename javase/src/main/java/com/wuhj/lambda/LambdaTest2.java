package com.wuhj.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;


/**
 * Lambda表达式的使用
 *
 * 1.举例 (o1,o2) -> Integer.compare(o1,o2)
 * 2.格式：
 *     ->:lambda操作符或箭头操作符
 *     ->左边：lambda形参列表(其实就是接口中的抽象方法的形参列表)
 *     ->右边：lambda体(其实就是重写的抽象方法的方法体)
 *
 * 3.lambda表达式的使用：(分为6种情况介绍)
 *     总结：
 *     ->左边：lambda形参列表的参数类型可以省略(类型);如果lambda形参列表只有一个参数，其一对()也可省略
 *     ->右边lambda体应该使用一对{}包裹；如果lambda体只有一条执行语句(可能是return语句)，可以省略一对{}和return关键字
 *
 * 4.lambda本质：作为接口的实列
 *     要求：函数式接口
 *
 * @author wuhj
 * @date 2020/6/23 15:58
 */
public class LambdaTest2 {
    
    //语法格式一：无参无返回值
    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱北京天安门");
            }
        };
        r1.run();
        System.out.println("*************");
        
        Runnable r2 = () -> System.out.println("我爱北京天故宫");
        r2.run();
    }
    
    //语法格式二：lambda需要一个参数，但是没有返回值
    @Test
    public void test2(){
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("谎言和誓言的区别是什么？");
        System.out.println("*************");
        
        Consumer<String> con1 = (String s) -> System.out.println(s);
        con1.accept("一个是听的人当真了，一个是说的人当真了");
    }
    
    //语法格式三：数据类型可以省略，因为可由编译器推断得出，称为“类型推断”
    @Test
    public void test3(){
        Consumer<String> con1 = (String s) -> System.out.println(s);
        con1.accept("一个是听的人当真了，一个是说的人当真了");
        System.out.println("*************");
        Consumer<String> con2 = (s) -> System.out.println(s);
        con2.accept("一个是听的人当真了，一个是说的人当真了");
    }
    
    //语法格式四：lambda若只需要一个参数时，参数的小括号可以省略
    @Test
    public void test4(){
        Consumer<String> con1 = (s) -> System.out.println(s);
        con1.accept("一个是听的人当真了，一个是说的人当真了");
        System.out.println("*************");
        Consumer<String> con2 = s -> System.out.println(s);
        con2.accept("一个是听的人当真了，一个是说的人当真了");
    }
    
    //语法格式五：lambda若需要两个或以上参数，多条执行语句，并且可以有返回值
    @Test
    public void tesr5(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare(12,21));
        System.out.println("*************");
    
        Comparator<Integer> com2 = (o1,o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        System.out.println(com2.compare(12,6));
    }
    
    //语法格式六：当lambda体只有一条语句时，renturn与大括号若有，都可以省略
    @Test
    public void test6(){
        Comparator<Integer> com1 = (o1,o2) -> {
            return o1.compareTo(o2);
        };
    
        System.out.println(com1.compare(12,6));
    
        System.out.println("*************");
    
        Comparator<Integer> com2 = (o1,o2) -> o1.compareTo(o2);
        System.out.println(com2.compare(12,12));
    }
    
    
}
