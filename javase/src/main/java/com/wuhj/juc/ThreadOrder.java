package com.wuhj.juc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @author wuhj
 * @date 2020/12/15 9:55
 */
public class ThreadOrder {
    //A:1 B:2 C:3
    private static int number = 1;
    ReentrantLock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    
    public void print5(){
        lock.lock();
        try {
            while (number != 1){
                condition1.await();
            }
            for (int i = 1; i < 2; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    
    public void print10(){
        lock.lock();
        try {
            while (number != 2){
                condition2.await();
            }
            for (int i = 1; i < 2; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    
    public void print15(){
        lock.lock();
        try {
            while (number != 3){
                condition3.await();
            }
            for(int i = 1; i < 2; i++){
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map1 = new ConcurrentHashMap();
        ThreadLocal threadLocal = new ThreadLocal();
        ThreadOrder threadOrder = new ThreadOrder();
    
        System.out.println("********************************");
        new Thread(() -> {for (int i = 0;i<15;i++){threadOrder.print5();}},"A").start();
        new Thread(() -> {for (int i = 0;i<15;i++){threadOrder.print10();}},"B").start();
        new Thread(() -> {for (int i = 0;i<15;i++){threadOrder.print15();}},"C").start();
        
        List<Map> list = new ArrayList();
        for(int i = 1; i < 6; i++) {
            HashMap map = new HashMap();
            map.put("id", i+"");
            map.put("name", "a" + i);
            
            list.add(map);
        }
        list.forEach(System.out :: println);
        System.out.println("**************");
        
        list.stream().filter(map -> !map.get("id").equals("1")).forEach(System.out :: println);
        List<Map> mapList = list.stream().filter(map -> !map.get("id").equals("1")).collect(Collectors.toList());
        System.out.println(mapList);
    }
}
