package com.mook.java.lang.clone;

import java.util.Objects;

/**
 * 深拷贝
 * 深克隆需要重写（Override）Object类的clone()方法，并且在方法内部调用持有对象的clone方法。
 */
public class MyFile2 implements Cloneable {
    private String path;

    private Info info;



    public MyFile2(String path, Info info) {

        this.path = path;

        this.info = info;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyFile2 myFile2 = (MyFile2) o;
        return Objects.equals(path, myFile2.path) &&
                Objects.equals(info, myFile2.info);
    }

    @Override
    public int hashCode() {

        return Objects.hash(path, info);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        MyFile2 file = (MyFile2) super.clone();

        file.info = (Info) file.info.clone();

        return file;

    }

    public static void main(String[] args) throws CloneNotSupportedException {

        Info info = new Info(2, "Hello world.");

        MyFile2 file1 = new MyFile2("c:", info);

        MyFile2 file2 = (MyFile2) file1.clone();



        System.out.println(file1.getClass() == file2.getClass());//true

        System.out.println(file1 == file2);//false

        System.out.println(file1.equals(file2));//true

        System.out.println(file1.info.getClass() == file2.info.getClass());//true

        System.out.println(file1.info == file2.info);//false

        System.out.println(file1.info.equals(file2.info));//true

    }

}
