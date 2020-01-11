package com.zxy.edu.eduservice.service;

import com.zxy.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxy.edu.eduservice.entity.form.CourseInfoForm;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zhangxy
 * @since 2020-01-09
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 添加课程信息
     *
     * @param courseInfoForm
     * @return
     */
    String insertCourseInfo(CourseInfoForm courseInfoForm);
}
