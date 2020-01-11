package com.zxy.edu.eduservice.controller;


import com.zxy.edu.common.R;
import com.zxy.edu.eduservice.entity.form.CourseInfoForm;
import com.zxy.edu.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author zhangxy
 * @since 2020-01-09
 */
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    /**
     * 添加课程信息的方法
     *
     * @return
     */
    @PostMapping
    public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
        String courseId = eduCourseService.insertCourseInfo(courseInfoForm);
        return R.ok().data("courseId", courseId);
    }
}

