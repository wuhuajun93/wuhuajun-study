package com.wuhj.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wuhj
 * @date 2020/11/23 9:19
 */
public class NotSafeDemo {
    
    public static void main(String[] args) {
        final Map<String, String> map = new ConcurrentHashMap<>();
        HashMap map1 = new HashMap(16, 0.75f);
        for (int i = 0; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
        
        
        
    }
}
