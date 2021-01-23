package com.wuhj.exception;

/**
 * 自定义异常
 *
 * @author wuhj
 * @date 2021/1/13 14:55
 */
public class CustomException extends Exception {
    
    public CustomException(String message) {
        super(message);
    }
}
