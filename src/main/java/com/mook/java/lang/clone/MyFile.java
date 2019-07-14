package com.mook.java.lang.clone;

import java.util.Objects;

/**
 * 浅拷贝
 */
public class MyFile implements Cloneable {
    private String path;

    private Info info;



    public MyFile(String path, Info info) {

        this.path = path;

        this.info = info;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyFile myFile = (MyFile) o;
        return Objects.equals(path, myFile.path) &&
                Objects.equals(info, myFile.info);
    }

    @Override
    public int hashCode() {

        return Objects.hash(path, info);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        return super.clone();

    }

    public static void main(String[] args) throws CloneNotSupportedException {

        Info info = new Info(2, "Hello world.");

        MyFile file1 = new MyFile("c:", info);

        MyFile file2 = (MyFile) file1.clone();



        System.out.println(file1.getClass() == file2.getClass());//true

        System.out.println(file1 == file2);//false

        System.out.println(file1.equals(file2));//true

        System.out.println(file1.info.getClass() == file2.info.getClass());//true

        System.out.println(file1.info == file2.info);//true

        System.out.println(file1.info.equals(file2.info));//true

    }

}
