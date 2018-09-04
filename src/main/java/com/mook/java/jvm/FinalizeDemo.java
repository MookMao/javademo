package com.mook.java.jvm;

/**
 * @Author: maojunkai
 * @Date: 2018/8/16 下午10:38
 * @Description:
 */
public class FinalizeDemo {
    public static void main(String[] args) throws InterruptedException {

        Test1 t1 = new Test1(new Test2("joker", 18));
        System.out.println(t1);
        System.out.println(t1.t2);

        t1 = null;
        // GC
        System.gc();
        Thread.sleep(10000);
        // 验证对象对象复活
        System.gc();
        System.out.println(Test3.t1);
        System.out.println(Test3.t1.t2);

        t1 = null;
        // 再次GC，测试finalize被JVM执行的次数
        System.gc();
        Thread.sleep(10000);
        System.out.println("done.");

    }
}

class Test1 {
    Test2 t2;

    public Test1(Test2 t2) {
        this.t2 = t2;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Test1 finalize...");
        Test3.t1 = this;
    }
}

class Test2 {
    String name;
    int age;

    public Test2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Test2 finalize...");
    }

    @Override
    public String toString() {
        return this.name + " is " + age;
    }
}

class Test3 {
    static Test1 t1;
}
