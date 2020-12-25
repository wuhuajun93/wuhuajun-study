package com.wuhj.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * stream 关注的是对数据的运算，与CPU打交道
 * 集合关注的是数据的存储，与内存打交道
 *
 * stream 自己不会存储元素
 * stream 不会改变源对象
 * stream 操作时延迟执行的，意味着它们会等到需要结果的时候才执行
 *
 * stream执行流程：
 * stream的实例化
 * 一系列中间操作(过滤，映射...)
 * 终止操作
 *
 * 说明：
 * 一个中间操作链，对数据源的数据进行处理
 * 一旦执行终止操作，就会执行中间操作链，并产生结果，之后，不会再被使用
 *
 * 测试stream的实例化
 * @author wuhj
 * @date 2020/12/16 9:14
 */
public class StreamAPITest {
    
    //创建stream方式一：通过集合
    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();
        //返回一个顺序流
        Stream<Employee> stream = employees.stream();
        //返回一个并行流
        Stream<Employee> employeeStream = employees.parallelStream();
    }
    
    //创建stream方式二：通过数组
    @Test
    public void test2(){
    
        int[] arr = {1,2,3,4,5,6};
        IntStream stream = Arrays.stream(arr);
    
    }
    
    //创建stream方式三：通过stream 的of()
    @Test
    public void test3(){
    
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
    }
    
    //创建stream方式四：创建无限流
    @Test
    public void test4(){
        //迭代
        Stream.iterate(0, t -> t+2).limit(10).forEach(System.out :: println);
        
        //生成
        Stream.generate(Math :: random).limit(10).forEach(System.out :: println);
    }
}
