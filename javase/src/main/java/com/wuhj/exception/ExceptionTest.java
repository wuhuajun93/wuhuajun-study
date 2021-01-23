package com.wuhj.exception;

/**
 * @author wuhj
 * @date 2021/1/13 14:57
 */
public class ExceptionTest {
    
    public static void main(String[] args){
        try {
            showException();
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }
    
    public static void showException() throws CustomException {
        throw new CustomException("除数不能为0");
    }
}
