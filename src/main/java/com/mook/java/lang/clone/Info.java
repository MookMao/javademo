package com.mook.java.lang.clone;

import java.util.Objects;

public class Info implements Cloneable {

    private int id;

    private String text;

    public Info(int id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return id == info.id &&
                Objects.equals(text, info.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {

        Info info1 = new Info(1, "I am Colyn Lu.");

        Info info2 = (Info) info1.clone();



        System.out.println(info1.getClass() == info2.getClass());//true

        System.out.println(info1 == info2);//false

        System.out.println(info1.equals(info2));//true

    }
}
