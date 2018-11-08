package com.mook.java.io.serializable;

/**
 * @Author: maojunkai
 * @Date: 2018/8/8 上午12:06
 * @Description:
 */
public class Main {
    public static void main(String[] args) throws Exception {
        SerialUtil.serializePerson();
        Person person = SerialUtil.deserializePerson();
        System.out.println(person.toString());

    }
}
