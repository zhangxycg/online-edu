package com.zxy.edu.eduservice.service;

import com.zxy.edu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zhangxy
 * @since 2020-01-07
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 读取Excel中的内容
     *
     * @param file
     */
    void importSubject(MultipartFile file);
}
