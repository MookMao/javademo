package com.mook.java.reflect;

import java.lang.reflect.Field;

/**
 * @Author: maojunkai
 * @Date: 2018/7/4 下午9:00
 * @Description: 运行时使用反射来分析对象：访问/设置Field对象表示的域
 */
public class DemoForRunningObj {
    private String name;

    private Double salary;

    public DemoForRunningObj(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

    public static void main(String[] args) {
        DemoForRunningObj obj = new DemoForRunningObj("jack", 22.0);
        printFieldInDemoForRunningObj(obj);
    }

    private static void printFieldInDemoForRunningObj(DemoForRunningObj obj) {
        Field field = null;
        Field field2 = null;
        try {
            // 可以查看对象的任意域，包括private修饰的
            field = obj.getClass().getDeclaredField("name");
            field2 = obj.getClass().getDeclaredField("salary");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            // 如果要修改对象private修饰的域，就需要为反射对象设置可访问标志
            field.setAccessible(true);
            field2.setAccessible(true);
            // 获取obj对象中用Field对象表示的域值
            System.out.println((String) field.get(obj));
            System.out.println((Double) field2.get(obj));

            // 用一个新值设置obj对象中Field对象表示的域
            field.set(obj, "jackReflect");
            System.out.println("设置之后：" + (String) field.get(obj));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
