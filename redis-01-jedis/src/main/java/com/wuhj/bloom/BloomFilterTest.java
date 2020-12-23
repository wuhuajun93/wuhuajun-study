package com.wuhj.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuhj
 * @date 2020/12/23 11:25
 */
public class BloomFilterTest {
    
    private static int size = 1000000;
    
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size);
    public static void main (String[]args){
        //加进布隆过滤器
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
        
        List<Integer> list = new ArrayList<Integer>(1000);
        //故意取10000个不在过滤器里的值，看看有多少个会被认为在过滤器里
        for (int i = size + 10000; i < size + 20000; i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        System.out.println("误判的数量：" + list.size());
    }
}
