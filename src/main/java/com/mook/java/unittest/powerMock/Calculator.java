package com.mook.java.unittest.powerMock;

/**
 * @Author: maojunkai
 * @Date: 2018/7/6 下午12:58
 * @Description:
 */
public class Calculator {
    private int sumXX(int a, int b) {
        return a + b;
    }

    public int callSumXX(int a, int b){
        return sumXX(a, b);
    }
}
