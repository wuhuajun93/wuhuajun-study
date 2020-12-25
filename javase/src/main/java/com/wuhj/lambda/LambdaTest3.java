package com.wuhj.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * java内置的4大核心函数式接口
 *
 *
 * 消费型接口 Consumer<T>    void accept()
 * 供给型接口 Supplier<T>    T get()
 * 函数型接口 Function<T,R>    R apply()
 * 断定型接口 Predicate<T>    boolean test(T t)
 *
 * @author wuhj
 * @date 2020/6/28 10:22
 */
public class LambdaTest3 {
    
    @Test
    public void test(){
        
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("买矿泉水，价格为："+ aDouble);
            }
        });
    
        System.out.println("***************************");
        
        happyTime(400, money -> System.out.println("买矿泉水，价格为："+ money));
    }
    
    public void happyTime(double money, Consumer<Double> con){
        con.accept(money);
    }
    
    
    @Test
    public void test2(){
        List<String> list = Arrays.asList("北京","南京","天津","东京","西京","普京");
        
        List<String> filterStrs = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        System.out.println(filterStrs);
        
        List<String> filterStr2 = filterString(list, s -> s.contains("京"));
        System.out.println(filterStr2);
    }
    
    public List<String> filterString(List<String> list, Predicate<String> pre){
        ArrayList<String> filterList = new ArrayList<>();
        for (String s : list){
            if (pre.test(s)){
                filterList.add(s);
            }
        }
        
        return filterList;
    }
}
