package com.mook.java.util.stream;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.primitives.Longs.asList;

/**
 * @Author: maojunkai
 * @Date: 2018/11/5 下午11:39
 * @Description: Stream常用操作
 */
public class StreamTest {
    class Student {
        Student(int age) {
            this.age = age;
        }

        int age;

        int getAge() {
            return age;
        }
    }

    private List<Apple> appleList = new ArrayList<>();

    @Before
    public void init() {
        Apple apple1 =  new Apple(1,"苹果1",new BigDecimal("3.25"),10);
        Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
        Apple apple2 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30);
        Apple apple3 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40);

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);
    }

    /**
     * Returns a stream consisting of the results of applying the given
     * function to the elements of this stream.
     */
    @Test
    public void test_map() {
        List<String> list = Stream.of("a", "b", "c").map(str -> str.toUpperCase()).collect(Collectors.toList());
        List<String> list2 = Stream.of("a", "b", "c").map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * mapToInt/mapToLong/mapToDouble
     */
    @Test
    public void test_mapToInt() {
        Integer sum = appleList.stream().mapToInt(Apple::getNum).sum();
        System.out.println(sum);
    }

    /**
     * 比map多了合并stream的过程
     */
    @Test
    public void test_flatMap() {
        // 如果有一个包含了多个集合的对象希望得到所有数字的集合，我们可以用flatMap：集合流合并
        List<Long> list = Stream.of(asList(1, 2), asList(3, 4))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        List<Long> list2 = Stream.of(asList(1, 2), asList(3, 4))
                .flatMap(list1 -> list1.stream())
                .collect(Collectors.toList());
        System.out.println(list);

        // flatMap 把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起
        // 最终 output 的新 Stream 里面已经没有 List 了，都是直接的数字。
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
    }

    /**
     * 去重
     * equals
     */
    @Test
    public void test_distinct() {
        List<Integer> list = Stream.of(1, 2, 2, 3, 4, 4).distinct().collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * Returns a stream consisting of the elements of this stream, sorted
     * according to natural order.  If the elements of this stream are not
     * {@code Comparable}, a {@code java.lang.ClassCastException} may be thrown
     * when the terminal operation is executed.
     */
    @Test
    public void test_sorted() {
        List<Integer> list = Stream.of(1, 2, 5, 3, 4, 4).sorted().collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void test_sorted_comparator() {
        List<Apple> list = appleList.stream().sorted((o1, o2) -> o2.getNum() - o1.getNum()).collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * 类似foreach，不同的就是peek会返回一个新的stream
     * 常用于debug
     *
     * 注意输出结果？？？？？
     */
    @Test
    public void test_peek() {
        List<String> list = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * limit 返回 Stream 的前面 n 个元素；skip 则是扔掉前 n 个元素
     */
    @Test
    public void test_limit() {
        List<String> list = Stream.of("one", "two", "three", "four")
                .map(String::toUpperCase)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * 对原始 Stream 进行某项测试，通过测试的元素被留下来生成一个新 Stream。
     *
     * Stream.of 根据数组创建流
     * 将流中的值进行过滤，并生成一个新的stream：遍历并检查其中的元素时，可用filter
     * 将流中的值生成一个List
     */
    @Test
    public void test_filter() {
        List<String> list = Stream.of("a", "ab", "abc")
                .filter(value -> value.contains("b"))
                .collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * Performs an action for each element of this stream.
     *
     * <p>This is a <a href="package-summary.html#StreamOps">terminal
     * operation</a>.
     */
    @Test
    public void test_forEach() {
        Stream.of("a", "ab", "abc")
                .filter(value -> value.contains("b"))
                .forEach(value -> System.out.println(value));
    }

    /**
     * stream -> 数组
     */
    @Test
    public void test_toArray() {
        Apple[] apples = appleList.stream().filter(apples1 -> apples1.getNum() > 20).toArray(Apple[]::new);
        System.out.println(JSON.toJSONString(apples));
    }

    /**
     * 这个方法的主要作用是把 Stream 元素组合起来。
     * 它提供一个起始值（种子），然后依照运算规则（BinaryOperator），和前面 Stream 的第一个、第二个、第 n 个元素组合。
     * 从这个意义上说，字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce。
     *
     * 有起始值的 reduce() 都返回具体的对象
     * 没有起始值的 reduce()，由于可能没有足够的元素，返回的是 Optional
     */
    @Test
    public void test_reduce() {
        // reduce操作可以实现从一组值中生成一个值，在上述例子中用到的count、min、max方法事实上都是reduce操作
        // 使用reduce求和，0表示起始值，acc表示累加器，保存着当前累加结果（每一步都将stream中的元素累加至acc），element是当前元素
        Integer reduce = Stream.of(1, 2, 3).reduce(0, (acc, element) -> acc + element);
        // IntStream
        Integer reduce2 = Stream.of(1, 2, 3).mapToInt(o -> o).sum();
        System.out.println(reduce);


        // 计算 总金额
        BigDecimal totalMoney = appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        Integer totalNum = appleList.stream().mapToInt(Apple::getNum).sum();
        // totalMoney:17.48
        System.out.println("totalMoney:" + totalMoney);
        System.out.println("totalNum:" + totalNum);

        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", (a, b) -> a.concat(b));
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);
    }

    /**
     * java.util.stream.Collectors 类的主要作用就是辅助进行各类有用的 reduction 操作
     * 例如转变输出为 Collection，把 Stream 元素进行归组。
     */
    @Test
    public void test_collect() {
        // Stream.of 根据数组创建流
        // 将流中的值生成一个List：collect(toList())方法由Stream里的值生成一个列表
        List<String> list = Stream.of("a", "b", "c").collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * List -> Map
     * 需要注意的是：
     * toMap 如果集合对象有重复的key，会报错Duplicate key ....
     * apple1,apple12的id都为1。
     */
    @Test
    public void test_list_to_map() {
        // Map<Integer, Apple> appleIdMap1 = appleList.stream().collect(Collectors.toMap(Apple::getId, apple -> apple));
        // System.out.println(JSON.toJSONString(appleIdMap1));

        Map<Integer, String> appleIdToNameMap = appleList.stream().collect(Collectors.toMap(Apple::getId, apple -> apple.getName(), (k1,k2)->k1));
        System.out.println(appleIdToNameMap);
    }

    /**
     * 分组：List里面的对象元素，以某个属性来分组
     * List 以ID分组 Map<Integer,List<Apple>>
     */
    @Test
    public void test_groupby() {
        Map<Integer, List<Apple>> appleGroupByWithId = appleList.stream().collect(Collectors.groupingBy(Apple::getId));
        System.out.println(appleGroupByWithId);
    }

    /**
     * an {@code Optional} describing the minimum element of this stream,
     * or an empty {@code Optional} if the stream is empty
     *
     * Optional
     */
    @Test
    public void test_Max_or_Min() {
        List<Student> list = Arrays.asList(new Student(1), new Student(2));
        Student student = list.stream()
                .min(Comparator.comparing(s -> s.getAge()))
                .get();
        Student student2 = list.stream()
                .min((s1, s2) -> s1.getAge() - s2.getAge())
                .get();
        System.out.println(student.getAge());
    }

    @Test
    public void test_count() {
        Long count = Arrays.asList(new Student(1), new Student(2)).stream().count();
        System.out.println(count);
    }

    /**
     * allMatch：Stream 中全部元素符合传入的 predicate，返回 true
     * anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
     * noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
     */
    @Test
    public void test_match() {
        boolean isStudentAgeEq2 = Arrays.asList(new Student(1), new Student(2)).stream().anyMatch(student -> student.getAge() == 2);
        System.out.println(isStudentAgeEq2);
    }

    /**
     * Optional<T> findFirst();
     * Optional<T> findAny();
     *
     * ？？？？？？
     */
    @Test
    public void test_findFirst() {
        Optional<Student> studentOptional = Arrays.asList(new Student(1), new Student(2)).stream().filter(student -> student.getAge() == 2).findFirst();
        System.out.println(JSON.toJSONString(studentOptional.get()));
    }
}
