package com.mook.java.keyword;

public class TestStatic {
    static {
        num2 = 40;
    }

    private static int num = initNum();

    static {
        num = 3;
        num2 = 10;
    }

    private static int num2 = 30;

    private static int initNum() {
        return 2;
    }

    public static void main(String[] args) {
        System.out.println(num); // 3
        System.out.println(num2); // 30
    }
}
