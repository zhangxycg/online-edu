package com.zxy.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxy.edu.eduservice.entity.EduTeacher;
import com.zxy.edu.eduservice.entity.query.QueryTeacher;
import com.zxy.edu.eduservice.handler.EduException;
import com.zxy.edu.eduservice.mapper.EduTeacherMapper;
import com.zxy.edu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author zhangxy
 * @since 2019-12-26
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    /**
     * 条件查询带分页
     *
     * @param pageTeacher
     * @param queryTeacher 查询的条件值
     */
    @Override
    public void pageListCondition(Page<EduTeacher> pageTeacher, QueryTeacher queryTeacher) {

        try {
            int i = 9/0;
        } catch (Exception e) {
            // 抛出自定义异常
            throw new EduException(20001,"执行了自定义异常");
        }

        // queryTeacher 有传递过来的条件值，在此进行判断。如果有条件，则进行拼接
        if (queryTeacher == null) {
            // 直接进行查询，不需要条件操作
            baseMapper.selectPage(pageTeacher, null);
            return;
        }

        // 如果查询条件不为空
        // 从queryTeacher里面取出查询条件的值
        String name = queryTeacher.getName();
        String level = queryTeacher.getLevel();
        String beginTime = queryTeacher.getBeginTime();
        String endTime = queryTeacher.getEndTime();

        // 判读条件值是否有值，若有值，则拼接条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(beginTime)) {
            wrapper.ge("gmt_create", beginTime); // ge 表示大于等于
        }
        if (!StringUtils.isEmpty(endTime)) {
            wrapper.le("gmt_create", endTime); // le 表示小于等于
        }

        // 条件查询带分页
        baseMapper.selectPage(pageTeacher, wrapper);
    }
}
