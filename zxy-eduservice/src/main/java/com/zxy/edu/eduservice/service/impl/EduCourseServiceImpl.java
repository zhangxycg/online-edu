package com.zxy.edu.eduservice.service.impl;

import com.zxy.edu.eduservice.entity.EduCourse;
import com.zxy.edu.eduservice.entity.EduCourseDescription;
import com.zxy.edu.eduservice.entity.form.CourseInfoForm;
import com.zxy.edu.eduservice.handler.EduException;
import com.zxy.edu.eduservice.mapper.EduCourseMapper;
import com.zxy.edu.eduservice.service.EduCourseDescriptionService;
import com.zxy.edu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zhangxy
 * @since 2020-01-09
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    // 为了在课程信息里面调用，注入课程描述的service
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    /**
     * 添加课程信息
     *
     * @param courseInfoForm
     * @return
     */
    @Override
    public String insertCourseInfo(CourseInfoForm courseInfoForm) {
        // 1. 添加课程基本信息到课程表
        // 把courseInfoForm里面的数据复制到eduCourse里面去，然后进行添加
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        int result = baseMapper.insert(eduCourse);
        // 判断如果添加课程信息成功，添加描述
        if (result == 0) { // 失败
            // 抛出异常
            throw new EduException(20001, "添加课程信息失败！");
        }

        // 2. 添加课程描述信息到课程描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        // 获取描述信息
        String description = courseInfoForm.getDescription();
        eduCourseDescription.setDescription(description);
        // 课程id
        String courseId = eduCourse.getId();
        eduCourseDescription.setId(courseId);

        boolean save = eduCourseDescriptionService.save(eduCourseDescription);
        if (save) {
            return courseId;
        } else {
            return null;
        }
    }
}
