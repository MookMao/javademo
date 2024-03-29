package com.mook.java.util;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
/**
 * @Author: maojunkai
 * @Date: 2019/1/15 4:48 PM
 * @Description: Calendar工具类测试
 */
public class CalendarTest {
    Calendar calendar = null;

    @Before
    public void test() {
        calendar = Calendar.getInstance();
    }

    // 基本用法，获取年月日时分秒星期
    @Test
    public void test1() {
        // 获取年
        int year = calendar.get(Calendar.YEAR);

        // 获取月，这里需要需要月份的范围为0~11，因此获取月份的时候需要+1才是当前月份值
        int month = calendar.get(Calendar.MONTH) + 1;

        // 获取日
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // 获取时
        int hour = calendar.get(Calendar.HOUR);
        // int hour = calendar.get(Calendar.HOUR_OF_DAY); // 24小时表示

        // 获取分
        int minute = calendar.get(Calendar.MINUTE);

        // 获取秒
        int second = calendar.get(Calendar.SECOND);

        // 星期，英语国家星期从星期日开始计算
        // 日 一  二 三 四 五 六 日
        // 1  2  3  4 5  6  7  1
        int weekday;
        if ((weekday = calendar.get(Calendar.DAY_OF_WEEK)) == 1) {
            weekday = 7;
        } else {
            weekday -= 1;
        }

        System.out.println("现在是" + year + "年" + month + "月" + day + "日" + hour
                + "时" + minute + "分" + second + "秒" + "星期" + weekday);
    }

    // 一年后的今天
    @Test
    public void test2() {
        // 同理换成下个月的今天calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.YEAR, 1);

        // 获取年
        int year = calendar.get(Calendar.YEAR);

        // 获取月
        int month = calendar.get(Calendar.MONTH) + 1;

        // 获取日
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("一年后的今天：" + year + "年" + month + "月" + day + "日");
    }

    // 获取任意一个月的最后一天
    @Test
    public void test3() {
        // 假设求6月的最后一天
        int currentMonth = 6;
        // 先求出7月份的第一天，实际中这里6为外部传递进来的currentMonth变量
        // 1
        calendar.set(calendar.get(Calendar.YEAR), currentMonth, 1);

        calendar.add(Calendar.DATE, -1);

        // 获取日
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("6月份的最后一天为" + day + "号");
    }

    // 设置日期
    @Test
    public void test4() {
        calendar.set(Calendar.YEAR, 2000);
        System.out.println("现在是" + calendar.get(Calendar.YEAR) + "年");

        calendar.set(2008, 8, 8);
        // 获取年
        int year = calendar.get(Calendar.YEAR);

        // 获取月
        int month = calendar.get(Calendar.MONTH);

        // 获取日
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("现在是" + year + "年" + month + "月" + day + "日");
    }

    /**
     * 日 一  二 三 四 五 六 日
     * 1  2  3  4 5  6  7  1
     */
    @Test
    public void getFirstDayOfThisWeek() {
        calendar.setTime(new Date());

        // 1 or 2
        int firstDayOfWeek = calendar.getFirstDayOfWeek();
        if (firstDayOfWeek == Calendar.SUNDAY) {
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
        }

        // 如果今天是周日，则按周六来算
        int thisDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) == 1 ? Calendar.SATURDAY : calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_MONTH, calendar.getFirstDayOfWeek() - thisDayOfWeek);
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
    }
}
