package com.mook.java.util.stream;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: maojunkai
 * @Date: 2018/11/6 上午11:41
 * @Description:
 */
@Data
public class Apple {
    private Integer id;
    private String name;
    private BigDecimal money;
    private Integer num;
    public Apple(Integer id, String name, BigDecimal money, Integer num) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.num = num;
    }
}

