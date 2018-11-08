package com.mook.java.io.serializable.serialVersionUID;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: maojunkai
 * @Date: 2018/11/7 下午11:01
 * @Description:
 */
@Data
public class BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long kdtId;
}
