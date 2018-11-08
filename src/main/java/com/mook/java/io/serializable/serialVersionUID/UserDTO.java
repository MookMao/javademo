package com.mook.java.io.serializable.serialVersionUID;

import lombok.Data;

/**
 * @Author: maojunkai
 * @Date: 2018/11/7 下午11:05
 * @Description:
 */
@Data
public class UserDTO extends BaseDTO {
//    private static final long serialVersionUID = 2L;
    private String userName;

    private String nickName;
}
