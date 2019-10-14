package com.mook.java.generic;

import net.sf.cglib.core.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 定义一个简单的泛型类
 * @param <T>
 */
public class Pair<T> {
    private T first;

    private T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

}

class ArrayAlg {

    /**
     * 泛型方法
     * 获取中间元素
     * @param a
     * @param <T>
     * @return
     */
    public static <T> T getMiddleElement(T... a) {
        if (a == null || a.length <= 0) {
            return null;
        }
        return a[a.length / 2];
    }

    /**
     * 泛型方法：获取数组最值
     * <T extends Comparable> ：泛型定义时设置类型限定
     * @param a
     * @return
     */
    public static <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length <= 0) {
            return new Pair(null, null);
        }
        // 不改变入参
        T[] temp = a.clone();
        List<T> aList = Arrays.asList(temp);
        aList = aList.stream().filter(obj -> obj != null).collect(Collectors.toList());
        if (org.springframework.util.CollectionUtils.isEmpty(aList)) {
            return new Pair<>(null, null);
        }
        T min = aList.get(0);
        T max = aList.get(0);
        for (T str : aList) {
            if (str.compareTo(min) < 0) {
                min = str;
            }
            if (str.compareTo(max) > 0) {
                max = str;
            }
        }
        return new Pair<>(min, max);
    }

    /**
     * 获取最小值
     * @param a
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T min(T[] a) {
        if (a == null || a.length <= 0) {
            return null;
        }
        T smallest = a[0];
        for (int i = 1; i < a.length; i++) {
            if (smallest.compareTo(a[i]) > 0) {
                smallest = a[i];
            }
        }
        return smallest;
    }

    public static void testGenericArray() {
        //List<String>[] lsa = new List<String>[10]; // error
        List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
        Object[] oa = (Object[]) lsa;
        List<Integer> li = new ArrayList<>();
        li.add(new Integer(3));
        oa[1] = li; // Correct.
        Integer i = (Integer) lsa[1].get(0);
        //String i = (String) lsa[1].get(0); // runtime: java.lang.ClassCastException
        System.out.println(i);
    }

    /**
     * 无法添加新元素的原因，是对于List<? extends Number>类型来说，可能是 List<Number>、 List<Integer>或List<Double>等类型
     * 无法确定新元素的类型与集合里的类型一致，所以编译器会提示报错。所以可以将List<? extends Number>类型的列表看作非严格意义上的只读列表。
     * 生产者-只读
     * List<?>等价于List<? extends Object>
     * @param c
     */
    public static void printCollection1(List<? extends Number> c) {
        c.size();
        // c.add(1); // 编译不过
        Number number = (Number) c.get(0);
        Object obj = (Object) c.get(0);
        Integer integer = (Integer) c.get(0); // 显式强制类型转换，运行时可能出现ClassCastException
        // String string = (String) c.get(0); // 编译不过
        for (Number o : c) {
            System.out.println(o);
        }

    }

    /**
     * 无法读取列表的原因，是对于List<? super Number>类型来说，可能是List<Number>也可能是List<Object>类型
     * 读取列表元素时不能确定元素类型。所以可以将List<? super Number>类型的列表看作只写列表。
     * @param c
     */
    public static void printCollection2(List<? super Number> c) {
        Object obj = new Object();
        c.size();
        c.add(Integer.valueOf(1));
        //   c.add(obj); // 编译不过，只能插入Number及其子类
        Object object = (Object) c.get(0);
        Number number = (Number) c.get(0);
        Integer integer = (Integer) c.get(0);
        String s = (String) c.get(0);

        for (Object o : c) {
            System.out.println(o);
        }

    }

    /**
     * 类型擦除之后，一个泛型类的Class对象都是同一个
     */
    public static void testGenericClass() {
        List<String> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        System.out.println(list1.getClass() == list2.getClass());
    }

    public static void main(String[] args) {
        System.out.println(minmax(new String[]{"a", "b", "c"})); // Pair{first=a, second=c}
        System.out.println(getMiddleElement("a", "b", "c"));
        System.out.println(getMiddleElement(1.14D, "2.0", 2));

        String[] aa = new String[10];
        Object[] bb = new Object[1];
        System.out.println(aa.getClass());
        System.out.println(bb.getClass());

//        testGenericArray();
//
//        testGenericClass();
//
//        printCollection1(Arrays.asList(1));
//        List<Number> list = new ArrayList<>();
//        list.add(1);
//        printCollection2(list);

        System.out.println((Object) Object[].class);

    }
}
