package com.wuhj.single;

import java.io.IOException;
import java.util.Properties;

/**
 * @author wuhj
 * @date 2021/2/7 15:22
 * 静态代码块-饿汉式
 */
public class Singleton3 {
    
    public static final Singleton3 INSTANCE;
    
    private String info;
    
    static {
        Properties pro = new Properties();
        try {
            pro.load(Singleton3.class.getClassLoader().getResourceAsStream("single.properties"));
            INSTANCE = new Singleton3(pro.getProperty("info"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    private Singleton3(String info){
        this.info = info;
    }
    
    public String getInfo() {
        return info;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }
    
    @Override
    public String toString() {
        return "Singleton3{" +
                "info='" + info + '\'' +
                '}';
    }
}
