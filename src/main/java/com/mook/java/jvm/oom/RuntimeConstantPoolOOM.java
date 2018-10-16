package com.mook.java.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: maojunkai
 * @Date: 2018/10/16 上午12:07
 * @Description: 运行时常量池溢出，借此测试一下运行时常量池在jdk8中存放的位置：去"永久代"？java堆？
 * -XX:PermSize=10M -XX:MaxPermSize=10M -XX:MaxMetaspaceSize=10m
 *
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
