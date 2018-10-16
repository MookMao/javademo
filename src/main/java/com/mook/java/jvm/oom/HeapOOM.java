package com.mook.java.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: maojunkai
 * @Date: 2018/10/14 下午11:42
 * @Description: Java 堆溢出
 * -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/heapdump.hprof
 */
public class HeapOOM {
    static class OOMObject { }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
