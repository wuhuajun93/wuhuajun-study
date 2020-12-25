package com.wuhj.stream;

import org.junit.Test;

import java.util.Optional;

/**
 * @author wuhj
 * @date 2020/12/16 15:25
 */
public class OptionalTest {
    
    @Test
    public void test1(){
        Girl girl = new Girl();
        Optional<Girl> optionalGirl = Optional.of(girl);
    }
    
    @Test
    public void test2(){
        Girl girl = new Girl();
        girl = null;
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);
    }
    
    public String getGirlName(Boy boy){
        return boy.getGirl().getName();
    }
    
    //优化以后的getGirlName方法
    public String getGirlName1(Boy boy){
        if (boy != null){
            Girl girl = boy.getGirl();
            if(girl != null){
                return girl.getName();
            }
        }
        return null;
    }
    
    //使用Optional的getGirlName方法
    public String getGirlName2(Boy boy){
    
        Optional<Boy> optionalBoy = Optional.ofNullable(boy);
    
        Boy boy1 = optionalBoy.orElse(new Boy(new Girl("迪丽热巴")));
        Girl girl = boy1.getGirl();
    
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        Girl girl1 = optionalGirl.orElse(new Girl("古力娜扎"));
        return girl1.getName();
    }
    
    @Test
    public void test3(){
        Boy boy = new Boy();
        String girlName = getGirlName1(boy);
        System.out.println(girlName);
    }
    
    @Test
    public void test4(){
        Boy boy = null;
        boy = new Boy();
        boy = new Boy(new Girl("苍老师"));
        String girlName = getGirlName2(boy);
        System.out.println(girlName);
    }
}
