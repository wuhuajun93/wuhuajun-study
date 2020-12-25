package com.wuhj.stream;

/**
 * @author wuhj
 * @date 2020/12/16 15:24
 */
public class Girl {
    private String name;
    
    public Girl() {
    }
    
    public Girl(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Girl{" +
                "name='" + name + '\'' +
                '}';
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
