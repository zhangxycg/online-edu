package com.zxy.edu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxy.edu.common.R;
import com.zxy.edu.eduservice.entity.EduTeacher;
import com.zxy.edu.eduservice.entity.query.QueryTeacher;
import com.zxy.edu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 1.查询所有的讲师
     *
     * @return
     */
    @GetMapping
    public R getAllTeacherList() {
        // 调用service的查询方法
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items", list);
    }

    /**
     * 2.逻辑删除讲师
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public boolean deleteTeacherById(@PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        return b;
    }

    /**
     * 3.分页查询讲师列表
     *
     * @param page  当前页
     * @param limit 每页记录数
     * @return
     */
    @GetMapping("pageList/{page}/{limit}")
    public R getPageTeacherList(@PathVariable Long page,
                                @PathVariable Long limit) {
        // 创建page对象，传递参数
        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        // 调用分页查询的方法
        eduTeacherService.page(pageTeacher, null);
        // 从pageTeacher对象中取出分页查询的数据
        // 总记录数
        long total = pageTeacher.getTotal();
        // 分页的数据
        List<EduTeacher> records = pageTeacher.getRecords();
        // 将数据返回到前台页面
        return R.ok().data("total", total).data("items", records);
    }

    /**
     * 4.多条件组合查询带分页
     *
     * @param page  当前页
     * @param limit 每页记录数
     * @return
     */
    @PostMapping("moreConditonPageList/{page}/{limit}")
    public R getMoreConditonPageList(@PathVariable Long page, @PathVariable Long limit,
                                     @RequestBody(required = false) QueryTeacher queryTeacher) {
        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        // 调用service层的方法实现条件查询并分页
        eduTeacherService.pageListCondition(pageTeacher, queryTeacher);
        // 从pageTeacher对象里面获取分页数据
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total", total).data("items", records);
    }


    /**
     * 5.添加讲师
     *
     * @param eduTeacher
     * @return
     */
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 6.根据id查询讲师
     *
     * @param id
     * @return
     */
    @GetMapping("getTeacherInfo/{id}")
    public R getTeacherInfo(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("eduTeacher", eduTeacher);
    }

    /**
     * 7.根据id进行修改讲师
     *
     * @param id
     * @return
     */
    @PostMapping("updateTeacher/{id}")
    public R updateTeacher(@PathVariable String id,
                           @RequestBody EduTeacher eduTeacher) {
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }

    }
}

