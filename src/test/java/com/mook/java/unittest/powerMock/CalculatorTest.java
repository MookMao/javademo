package com.mook.java.unittest.powerMock;

import org.junit.*;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

/**
 * @Author: maojunkai
 * @Date: 2018/7/6 下午12:58
 * @Description:
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Calculator.class})
public class CalculatorTest {

    private Calculator test;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        test = new Calculator();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSumXX() throws Exception {
        Calculator powerMock = PowerMockito.spy(new Calculator());
        PowerMockito.when(powerMock, "sumXX", 1, 2).thenReturn(2);
        assertEquals(2, powerMock.callSumXX(1, 2));
    }
}

