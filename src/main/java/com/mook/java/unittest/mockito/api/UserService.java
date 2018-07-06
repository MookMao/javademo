package com.mook.java.unittest.mockito.api;

import com.mook.java.unittest.mockito.entity.User;

/**
 * @Author: maojunkai
 * @Date: 2018/7/5 下午11:50
 * @Description:
 */
public interface UserService {
    /**
     * 创建新用戶
     */
    void createNewUser(User user) throws Exception ;

    Long count();
}
