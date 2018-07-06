package com.mook.java.unittest.powerMock;

import com.mook.java.unittest.mockito.dao.UserDao;
import com.mook.java.unittest.mockito.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @Author: maojunkai
 * @Date: 2018/7/6 上午11:00
 * @Description: Junit测试框架 + Mockito + PowerMock
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({StringUtil.class, UserServiceImpl.class})
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    // 当时使用@RunWith(PowerMockRunner.class)时，就可以省去MockitoAnnotations.initMocks(this);
//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void test_callSystemStaticMethod() {
        // mock系统类的静态方法 + mockito
        PowerMockito.mockStatic(System.class);
        Mockito.when(System.getProperty(Mockito.anyString())).thenReturn("mock静态方法");
        String res = userService.callSystemStaticMethod("任意字符串");
        Assert.assertTrue("mock静态方法".equals(res));
    }

    @Test
    public void test_equals() {
        // mock普通类的静态方法 + mockito
        PowerMockito.mockStatic(StringUtil.class);
        Mockito.when(StringUtil.equals(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        boolean res = userService.equals("1", null);
        Assert.assertTrue(res);
    }

    // 测试私有方法
    @Test
    public void testCallPrivateMethod() throws Exception
    {
        // PowerMockito.mock是全部mock；PowerMockito.spy是部分mock
        UserServiceImpl userService = PowerMockito.mock(UserServiceImpl.class);
        PowerMockito.when(userService.callPrivateMethod()).thenCallRealMethod();
        // mock私有方法
        PowerMockito.when(userService, "isExist").thenReturn(true);
        Assert.assertTrue(userService.callPrivateMethod());
    }
}
