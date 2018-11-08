package com.mook.java.io.serializable;

import java.io.Serializable;

/**
 * @Author: maojunkai
 * @Date: 2018/8/8 上午12:34
 * @Description:
 */
public class People {
    int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public People(){
//        num = 20;
        System.out.println("父类无参构造");
    }  			//默认的无参构造函数，没有进行初始化
    public People(int num){		//有参构造函数
        System.out.println("父类有参构造");
        this.num = num;
    }

    @Override
    public String toString(){
        return "{" + "num:" + num + "}";
    }
}
