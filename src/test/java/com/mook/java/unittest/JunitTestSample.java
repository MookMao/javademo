package com.mook.java.unittest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @Author: maojunkai
 * @Date: 2018/7/5 下午11:20
 * @Description: 测试类，使用Junit框架
 */
public class JunitTestSample {
    private Calculator calculator = new Calculator();

    @Before
    public void setUp() throws Exception
    {
        calculator.clear();
    }

    @Test
    public void testAdd()
    {
        calculator.add(1);
        calculator.add(2);
        int result = calculator.getResult();
        Assert.assertEquals(3, result);
    }

    @Ignore
    @Test
    public void testSubstract()
    {
        calculator.add(10);
        calculator.substract(2);
        int result = calculator.getResult();
        Assert.assertEquals(8, result);
    }

    @Test(timeout=3000)
    public void testDivide()
    {
        calculator.add(10);
        calculator.divide(2);
        int result = calculator.getResult();
        Assert.assertEquals(5, result);
    }
}
