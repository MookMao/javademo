package com.mook.java.unittest.powerMock;

/**
 * @Author: maojunkai
 * @Date: 2018/7/6 上午11:18
 * @Description:
 */
public class StringUtil {
    public static boolean equals(String a, String b) {
        if (a == null || b == null) {
            return false;
        }
        return a.equals(b);
    }
}
