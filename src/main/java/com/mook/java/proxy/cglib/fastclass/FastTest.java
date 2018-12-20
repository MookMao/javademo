package com.mook.java.proxy.cglib.fastclass;

/**
 * @Author: maojunkai
 * @Date: 2018/12/13 上午12:04
 * @Description:
 */
class FastTest {
    public int getIndex(String signature){
        switch(signature.hashCode()){
            case 3078479:
                return 1;
            case 3108270:
                return 2;
        }
        return -1;
    }

    public Object invoke(int index, Object o, Object[] ol){
        Test t = (Test) o;
        switch(index){
            case 1:
                t.f();
                return null;
            case 2:
                t.g();
                return null;
        }
        return null;
    }
}
