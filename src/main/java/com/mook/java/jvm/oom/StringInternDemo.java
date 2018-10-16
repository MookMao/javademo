package com.mook.java.jvm.oom;

/**
 * @Author: maojunkai
 * @Date: 2018/10/16 上午12:27
 * @Description: String.intern()
 */
public class StringInternDemo {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("enen").toString();
        // true
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("j").append("ava").toString();
        // false
        System.out.println(str2.intern() == str2);
    }
}
