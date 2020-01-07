package com.zxy.edu.eduservice.controller;


import com.zxy.edu.common.R;
import com.zxy.edu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    /**
     * 通过上传Excel文件获取文件内容
     *
     * @return
     */
    @PostMapping("import")
    public R importExcelSubject(@RequestParam("file") MultipartFile file) {
        // 1. 获取上传的Excel文件
        List<String> msg = eduSubjectService.importSubject(file);
        return R.ok().data("msgList", msg);
    }
}

