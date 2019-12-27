package com.zxy.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxy.edu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxy.edu.eduservice.entity.query.QueryTeacher;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author zhangxy
 * @since 2019-12-26
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 条件查询带分页
     *
     * @param pageTeacher
     * @param queryTeacher 查询的条件值
     */
    void pageListCondition(Page<EduTeacher> pageTeacher, QueryTeacher queryTeacher);
}
