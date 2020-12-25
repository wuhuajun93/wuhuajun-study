package com.wuhj.stream;

/**
 * @author wuhj
 * @date 2020/12/16 15:24
 */
public class Boy {
    
    private Girl girl;
    
    public Boy() {
    }
    
    public Boy(Girl girl) {
        this.girl = girl;
    }
    
    public Girl getGirl() {
        return girl;
    }
    
    public void setGirl(Girl girl) {
        this.girl = girl;
    }
    
    @Override
    public String toString() {
        return "Boy{" +
                "girl=" + girl +
                '}';
    }
}
