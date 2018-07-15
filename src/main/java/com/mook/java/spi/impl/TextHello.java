package com.mook.java.spi.impl;

import com.mook.java.spi.api.HelloInterface;

/**
 * @Author: maojunkai
 * @Date: 2018/7/15 下午5:57
 * @Description:
 */
public class TextHello implements HelloInterface {

    public void sayHello() {
        System.out.println("Text Hello.");
    }

}
