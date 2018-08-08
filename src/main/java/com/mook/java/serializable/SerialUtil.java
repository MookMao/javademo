package com.mook.java.serializable;

import java.io.*;

/**
 * @Author: maojunkai
 * @Date: 2018/8/7 下午11:49
 * @Description:
 */
public class SerialUtil {

    private static String PATH = "/Users/mook/IdeaProjects/JavaDemo/src/main/resources/serializable/person";

    /**
     * 序列化
     */
    public static void serializePerson() throws IOException {
        Person person = new Person();
        person.setAge(12);
        person.setName("naruto");

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(PATH));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(person);
        }
        System.out.println("Person 对象序列化成功！");

        // ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("d:/person.txt")));
        // oos.writeObject(person);
        // System.out.println("Person 对象序列化成功！");
        // oos.close();
    }

    /**
     * 反序列化
     */
    public static Person deserializePerson() throws Exception {
        Person person = null;
        try (FileInputStream  fileInputStream = new FileInputStream(new File(PATH));
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            person = (Person) objectInputStream.readObject();
        }
        System.out.println("反序列化成功！");
        return person;
    }
}
