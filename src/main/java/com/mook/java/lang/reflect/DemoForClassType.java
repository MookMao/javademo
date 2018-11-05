package com.mook.java.lang.reflect;

/**
 * @Author: maojunkai
 * @Date: 2018/7/4 上午10:50
 * @Description: 获取Class对象的三种方式
 */
public class DemoForClassType {

    private static void useClass() {
        // 得到Class对象
        Class aClass = DemoForClassType.class;
        // 全类名，包括路径信息
        System.out.println("Class对象（DemoForClassType.class）：" + aClass.getName());
        // 类名
        System.out.println("Class对象（DemoForClassType.class）：" + aClass.getSimpleName());
        System.out.println("Class对象（int.class）：" + int.class.getName());
        System.out.println("Class对象（Double[].class）：" + Double[].class.getName());
    }

    private static void useClass2() {
        try {
            // 参数必须是类名或接口名，否则会抛出已检查异常
            Class aClass = Class.forName("com.mook.java.lang.reflect.DemoForClassType");
            System.out.println("Class对象（Class.forName）：" + aClass.getConstructors());
            System.out.println("Class对象（Class.forName）：" + aClass.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static void useClass3() {
        DemoForClassType demo = new DemoForClassType();
        Class aClass = demo.getClass();
        System.out.println("Class对象（object.getClass()）：" + aClass.getSimpleName());
    }

    public static void main(String[] args) {
        useClass();
        System.out.println("-----------------");
        useClass2();
        System.out.println("-----------------");
        useClass3();
    }
}
