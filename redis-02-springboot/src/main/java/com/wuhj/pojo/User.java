package com.wuhj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author wuhj
 * @date 2020/12/18 11:25
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    
    private String name;
    private int age;
    
}
