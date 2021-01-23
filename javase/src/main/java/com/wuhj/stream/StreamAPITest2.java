package com.wuhj.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wuhj
 * @date 2020/12/16 11:05
 */
public class StreamAPITest2 {
    
    //1.匹配与查找
    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();
        //allMatch(Predicate p)——检查是否匹配所有元素
        //练习：是否所有员工的年龄都大于18
        boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);
        //anyMath(Predicate p)——检查是否至少匹配一个元素
        //练习：是否存在员工的工资大于10000
        boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(anyMatch);
        //noneMatch(Predicate p)——检查是否没有匹配的元素
        //练习：是否存在员工姓 雷
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().contains("雷"));
        System.out.println(noneMatch);
        //findFirst——返回第一个元素
        Optional<Employee> employee = employees.stream().findFirst();
        System.out.println(employee);
    
        //findAny——返回当前流中的任意元素
        Optional<Employee> employee1 = employees.stream().findAny();
        System.out.println(employee1);
        //count——返回流中元素的总个数
        long count = employees.stream().filter(e -> e.getSalary() > 5000).count();
        System.out.println(count);
        //max(Comparator c)——返回流中的最大值
        //练习：返回最高的工资的员工
        Optional<Double> maxSalary = employees.stream().map(e -> e.getSalary()).max(Double::compareTo);
        System.out.println(maxSalary);
        //min(Comparator c)——返回流中最小值
        //练习：返回最低工资的员工
        Optional<Employee> e = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(e);
        
        //forEach(Consume c)
        employees.stream().forEach(System.out :: println);
    }
    
    //2.规约
    @Test
    public void test2(){
    
        //reduce(T identity, BinaryOperator)——可以将流中的元素反复结合起来，得到一个值，返回一个T
        //练习：计算1-10的自然数的和
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer reduce = list.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
        //reduce(BinaryOperator)——可以将流中的元素反复结合起来，得到一个值，返回Optional<T>
        //练习：计算公司的所有员工工资的总和
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Double> doubleStream = employees.stream().map(Employee::getSalary);
        Optional<Double> sumMoney = doubleStream.reduce(Double::sum);
        System.out.println(sumMoney);
    }
    
    //收集
    @Test
    public void test3(){
        //collect(Collector c)——将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
        //练习：查找工资大于6000的员工，结果返回为一个list或set
        List<Employee> employees = EmployeeData.getEmployees();
    
        List<Employee> list = employees.stream().filter(employee -> employee.getSalary() > 6000).collect(Collectors.toList());
        list.forEach(System.out :: println);
    }
}
