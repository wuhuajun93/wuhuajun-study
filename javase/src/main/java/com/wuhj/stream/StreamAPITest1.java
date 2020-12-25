package com.wuhj.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 测试stream的中间操作
 *
 * @author wuhj
 * @date 2020/12/16 9:42
 */
public class StreamAPITest1 {
    
    //1.筛选与切片
    @Test
    public void test(){
        List<Employee> list = EmployeeData.getEmployees();
        Stream<Employee> stream = list.stream();
        //练习：查询员工中薪资大于7000的员工信息
        stream.filter(employee -> employee.getSalary() > 7000).forEach(System.out :: println);
    
        System.out.println("***********************");
        //截断流 limit()
        list.stream().limit(3).forEach(System.out :: println);
        System.out.println("***********************");
        //跳过元素 skip()
        list.stream().skip(3).forEach(System.out :: println);
        System.out.println("***********************");
        //筛选 distinct()，通过新生成元素的hashcode() 和 equals() 去除重复元素
        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 41, 8000));
    
        list.stream().distinct().forEach(System.out :: println);
    }
    
    //2.映射
    @Test
    public void test2(){
        
        //map(Function f)——接收一个函数作为参数，将元素转换为其他形式或提取信息，该函数会被应用到每一个元素上，并将其映射成一个新的元素
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out :: println);
    
        //练习1：获取员工姓名长度大于3的姓名
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().map(employee -> employee.getName()).filter(employee -> employee.length() > 3).forEach(System.out :: println);
        
        //练习2：
        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest1::formStringToStream);
        streamStream.forEach(s -> {
            s.forEach(System.out :: println);
        });
        System.out.println();
        //flatMap(Function f)——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
        Stream<Character> characterStream = list.stream().flatMap(s -> formStringToStream(s));
        characterStream.forEach(System.out :: println);
    
    }
    
    //将字符串中的多个字符构成的集合转换为对应的stream的实例
    public static Stream<Character> formStringToStream(String str){
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }
    
    @Test
    public void test3(){
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
    
        ArrayList list2 = new ArrayList();
        list2.add(4);
        list2.add(5);
        list2.add(6);
        
        list.addAll(list2);
        System.out.println(list);
    }
    
    //3.排序
    @Test
    public void test4(){
        //sorted——自然排序
        List<Integer> list = Arrays.asList(12, 43, 65, 34, 87, 0, -98, 7);
        list.stream().sorted().forEach(System.out :: println);
        //没有实现Comparable接口，抛异常
//        List<Employee> employees = EmployeeData.getEmployees();
//        employees.stream().sorted().forEach(System.out :: println);
        
        //sorted(Comparator com)——定制排序
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted((e1, e2) -> {
            int ageValue = Integer.compare(e1.getAge(), e2.getAge());
            if(ageValue != 0){
                return ageValue;
            }else {
                return -Double.compare(e1.getSalary(), e2.getSalary());
            }
        }).forEach(System.out :: println);
    }
}
