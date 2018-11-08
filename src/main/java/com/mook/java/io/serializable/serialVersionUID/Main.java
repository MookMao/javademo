package com.mook.java.io.serializable.serialVersionUID;

import com.alibaba.fastjson.JSON;
import com.mook.java.io.serializable.Person;
import com.mook.java.io.serializable.SerialUtil;

import java.io.*;

/**
 * @Author: maojunkai
 * @Date: 2018/11/7 下午11:06
 * @Description: 检测子类是否用的是父类的serialVersionUID
 *
 * 父类和子类使用的serialVersionUID没有关系。
 * 所以当父类实现了Serializable接口，子类不需要再实现Serializable接口，
 * 但是子类还是最好给个serialVersionUID值（private static final long serialVersionUID = 1L;），
 * 不然的话子类就会默认使用java自动生成的一个serialVersionUID值。
 */
public class Main {
    private static String PATH = "/Users/mook/IdeaProjects/JavaDemo/src/main/resources/serializable/person";

    public static void main(String[] args) throws Exception {
//        serialize();

        deserialize();
    }

    private static void serialize() throws IOException {
        UserDTO userDTO = new UserDTO();
        userDTO.setKdtId(1111L);
        userDTO.setUserName("Lily");

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(PATH));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(userDTO);
        }
    }

    private static void deserialize() throws Exception {
        UserDTO userDTO1 = null;
        try (FileInputStream fileInputStream = new FileInputStream(new File(PATH));
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            userDTO1 = (UserDTO) objectInputStream.readObject();
        }
        System.out.println(JSON.toJSONString(userDTO1));
        System.out.println(userDTO1);
    }
}
