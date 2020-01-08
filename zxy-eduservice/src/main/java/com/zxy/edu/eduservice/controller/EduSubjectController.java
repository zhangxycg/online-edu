package com.zxy.edu.eduservice.controller;


import com.zxy.edu.common.R;
import com.zxy.edu.eduservice.entity.dto.OneSubjectDto;
import com.zxy.edu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author zhangxy
 * @since 2020-01-07
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    /**
     * 1. 通过上传Excel文件获取文件内容
     *
     * @return
     */
    @PostMapping("import")
    public R importExcelSubject(@RequestParam("file") MultipartFile file) {
        // 获取上传的Excel文件
        List<String> msg = eduSubjectService.importSubject(file);
        if (msg.size() == 0) {
            return R.ok();
        } else {
            return R.error().message("部分数据导入失败").data("msgList", msg);
        }
    }

    /**
     * 2. 返回所有分类数据，返回要求的json数据格式
     *
     * @return
     */
    @GetMapping
    public R getAllSubjectList() {
        List<OneSubjectDto> list = eduSubjectService.getSubjectList();
        return R.ok().data("OneSubjectDto", list);
    }

    /**
     * 3. 删除一级分类
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public R deleteSubjectId(@PathVariable String id) {
        boolean flag = eduSubjectService.deleteSubjectById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

