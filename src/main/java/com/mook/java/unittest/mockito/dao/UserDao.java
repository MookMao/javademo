package com.mook.java.unittest.mockito.dao;

import com.mook.java.unittest.mockito.entity.User;

/**
 * @Author: maojunkai
 * @Date: 2018/7/5 下午11:48
 * @Description:
 */
public interface UserDao {
    /**
     * 新增用戶
     */
    void insertUser(User user);

    /**
     * 查詢用戶
     */
    User queryUser(Long id);

    Long count();
}
