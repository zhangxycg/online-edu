package com.zxy.edu.eduservice.controller;


import com.zxy.edu.eduservice.entity.EduTeacher;
import com.zxy.edu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zhangxy
 * @since 2019-12-26
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 查询所有的讲师
     *
     * @return
     */
    @GetMapping
    public List<EduTeacher> getAllTeacherList() {
        // 调用service的查询方法
        List<EduTeacher> list = eduTeacherService.list(null);
        return list;
    }
}

