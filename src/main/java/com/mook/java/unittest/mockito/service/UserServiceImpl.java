package com.mook.java.unittest.mockito.service;

import com.mook.java.unittest.mockito.api.UserService;
import com.mook.java.unittest.mockito.dao.UserDao;
import com.mook.java.unittest.mockito.entity.User;
import com.mook.java.unittest.powerMock.StringUtil;

/**
 * @Author: maojunkai
 * @Date: 2018/7/5 下午11:51
 * @Description:
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void createNewUser(User user) throws Exception {
        // 参数校验
        if (user == null || user.getId() == null || isEmpty(user.getName())) {
            throw new IllegalArgumentException();
        }

        // 查看是否是重复数据
        Long id = user.getId();
        User dbUser = userDao.queryUser(id);
        if (dbUser != null) {
            throw new Exception("用户已经存在");
        }

        try {
            userDao.insertUser(dbUser);
        } catch (Exception e) {
            // 隐藏Database异常，抛出服务异常
            throw new Exception("数据库语句执行失败", e);
        }
    }

    public Long count() {
        return userDao.count();
    }

    private boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String callSystemStaticMethod(String str) {
        return System.getProperty(str);
    }

    public boolean equals(String str, String str2) {
        return StringUtil.equals(str, str2);
    }

    // 测试mock私有方法
    public boolean callPrivateMethod() {
        return isExist();
    }

    private boolean isExist() {
        return false;
    }
}
