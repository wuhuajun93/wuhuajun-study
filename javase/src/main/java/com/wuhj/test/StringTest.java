package com.wuhj.test;

import java.util.Arrays;

/**
 * @author wuhj
 * @date 2020/12/8 15:52
 */
public class StringTest {
    
    public static void main(String[] args) {
        
        /*//统计一个字符串在另一个字符串中出现的次数
        String str = "abkkcadkabkebfkabkskab";
    
        //方式一
        String ab = str.replace("ab", ",");
        String[] split = ab.split(",");
        System.out.println(split.length);
        //方式二
        int index = 0;
        int count = 0;
        while ((index = str.indexOf("ab", index)) != -1){
            count++;
            index++;
        }
        System.out.println(count);*/
        
        /*//获取两个字符串中最大相同字串
        String a = "abcwerthelloyuiodef";
        String b = "cvhellobnm";
        List<String> list = new ArrayList<>();
        for (int i = 0; i < b.length(); i++) {
            for (int j = 0; j < i+1; j++) {
                if(a.contains(b.substring(j, b.length() + j - i))){
                    list.add(b.substring(j, b.length() + j - i));
                }
            }
        }
        //取出最长相同字符串，可能又多个，但是第一个一定是最长的
        if(list != null && list.size() > 0){
            int length = list.get(0).length();
            for (int i = 0; i < list.size(); i++) {
                if(length == list.get(i).length()){
                    System.out.println(list.get(i));
                }
            }
        }*/
        
        //对字符串中的字符进行自然顺序排序
        String a = "abc0wer7thelloyuiodef";
        char[] chars = a.toCharArray();
        Arrays.sort(chars);
    
        System.out.println(chars);
    
        String str1 = "abcwerthelloyuiodef";
        String str2 = "cvhellobnm";
        String sameStr = gatMaxSameString(str1, str2);
    
        System.out.println("sameStr : " + sameStr);
    
    
        for (int i = 0; i < "我ABC汉DEF".length(); i++) {
            System.out.println("我ABC汉DEF".charAt(i));
        }
    
        System.out.println(subString("我ABC汉DEF", 6));
    }
    
    public static String gatMaxSameString(String maxStr, String minStr) {
        int lenght = minStr.length();
        
        for (int i = 0; i < lenght; i++) {
            for(int x = 0,y = lenght - i; y <= lenght; x++,y++) {
                String subStr = minStr.substring(x, y);
                if(maxStr.contains(subStr)){
                    return subStr;
                }
            }
        }
        return null;
    }
    
    public static String subString(String str, int subBytes) {
        int bytes = 0; // 用来存储字符串的总字节数
        for (int i = 0; i < str.length(); i++) {
            if (bytes == subBytes) {
                return str.substring(0, i);
            }
            char c = str.charAt(i);
            if (c < 256) {
                bytes += 1; // 英文字符的字节数看作1
            } else {
                bytes += 2; // 中文字符的字节数看作2
                if(bytes - subBytes == 1){
                    return str.substring(0, i);
                }
            }
        }
        return str;
    }
}
