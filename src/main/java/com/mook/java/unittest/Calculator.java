package com.mook.java.unittest;

/**
 * @Author: maojunkai
 * @Date: 2018/7/5 下午11:09
 * @Description: 待测试的业务类
 */
public class Calculator {
    // 静态变量，用于存储运行结果
    private static int result;

    public void add(int n) {
        result += n;
    }

    public void substract(int n) {
        result -= n;
    }

    public void multiply(int n) {
        result *= n;
    }

    public void divide(int n) {
        result /= n;
    }

    public void square(int n) {
        result = n * n;
    }

    // 将结果清零
    public void clear() {
        result = 0;
    }

    public int getResult() {
        return result;
    }

}
