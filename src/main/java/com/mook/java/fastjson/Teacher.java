package com.mook.java.fastjson;

import lombok.Data;

import java.util.List;

/**
 * @Author: maojunkai
 * @Date: 2018/10/14 下午2:53
 * @Description:
 */
@Data
public class Teacher {

    private String teacherName;
    private Integer teacherAge;
    private Course course;
    private List<Student> students;

}
