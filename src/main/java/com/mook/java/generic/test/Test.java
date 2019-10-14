package com.mook.java.generic.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Test {
    public static void main(String[] args) {

    }

    private static <T> void deal(BiConsumer<T, List<String>> action, List<T> list, String userId) {
        List<String> ids = new ArrayList<>();
        for (T t : list) {
            action.accept(t, ids);
        }
    }
}
