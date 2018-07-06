package com.mook.java.unittest.mockito.service;

import com.mook.java.unittest.mockito.api.UserService;
import com.mook.java.unittest.mockito.dao.UserDao;
import com.mook.java.unittest.mockito.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.sql.SQLException;

import static org.mockito.Matchers.any;

/**
 * @Author: maojunkai
 * @Date: 2018/7/5 下午11:53
 * @Description: Junit测试框架 + Mockito
 */
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUser() throws Exception {
        // UserService userService = new UserServiceImpl();

        // 创建mock
        // UserDao userDao = mock(UserDao.class);
        // ((UserServiceImpl) userService).setUserDao(userDao);

        userService.createNewUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUserId() throws Exception {
        // UserService userService = new UserServiceImpl();

        // 创建mock
        // UserDao userDao = mock(UserDao.class);
        // ((UserServiceImpl) userService).setUserDao(userDao);

        User user = new User();
        user.setId(null);
        userService.createNewUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUserName() throws Exception {
        // UserService userService = new UserServiceImpl();

        // 创建mock
        // UserDao userDao = mock(UserDao.class);
        // ((UserServiceImpl) userService).setUserDao(userDao);

        User user = new User();
        user.setId(1L);
        user.setName("");
        userService.createNewUser(user);
    }

    @Test(expected = Exception.class)
    public void testCreateExistUser() throws Exception {
        // UserService userService = new UserServiceImpl();

        // 创建mock
        // UserDao userDao = mock(UserDao.class);
        User returnUser = new User();
        returnUser.setId(1L);
        returnUser.setName("Vikey");
        Mockito.when(userDao.queryUser(1L)).thenReturn(returnUser);
        // ((UserServiceImpl) userService).setUserDao(userDao);

        User user = new User();
        user.setId(1L);
        user.setName("Vikey");
        userService.createNewUser(user);
    }

    @Test(expected = Exception.class)
    public void testCreateUserOnDatabaseException() throws Exception {
        // UserService userService = new UserServiceImpl();

        // 创建mock
        // UserDao userDao = mock(UserDao.class);
        Mockito.doThrow(new SQLException("SQL is not valid")).when(userDao).insertUser(any(User.class));
        // mock void方法
        // Mockito.doNothing().when(userDao).insertUser(any(User.class));
        // ((UserServiceImpl) userService).setUserDao(userDao);

        User user = new User();
        user.setId(1L);
        user.setName("Vikey");
        userService.createNewUser(user);
    }

    @Test
    public void testCreateUser() throws Exception {
        // UserService userService = new UserServiceImpl();

        // 创建mock
        // UserDao userDao = mock(UserDao.class);
        Mockito.doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) throws Throwable {
                System.out.println("Insert data into user table");
                return null;
            }
        }).when(userDao).insertUser(any(User.class));
        // ((UserServiceImpl) userService).setUserDao(userDao);

        User user = new User();
        user.setId(1L);
        user.setName("Vikey");
        userService.createNewUser(user);
    }

    @Test
    public void testCountUsers() {
        Mockito.when(userDao.count()).thenReturn(5L);
        Long res = userService.count();
        Assert.assertEquals(5, res.longValue());
    }
}
