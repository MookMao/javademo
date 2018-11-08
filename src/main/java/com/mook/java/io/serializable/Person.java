package com.mook.java.io.serializable;

import java.io.Serializable;

/**
 * @Author: maojunkai
 * @Date: 2018/8/7 下午11:41
 * @Description:
 */

public class Person extends People implements Serializable {

    private static final long serialVersionUID = 5293828023152714409L;

    private String name;

    private static int height = 200;

    private transient int age = 24;

    public Person() {
        System.out.println("无参构造");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("构造函数");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Person.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", super=" + super.toString() +
                '}';
    }
}
