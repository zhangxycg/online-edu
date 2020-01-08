package com.zxy.edu.eduservice.service;

import com.zxy.edu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxy.edu.eduservice.entity.dto.OneSubjectDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     * @return
     */
    List<String> importSubject(MultipartFile file);

    /**
     * 返回所有分类
     *
     * @return
     */
    List<OneSubjectDto> getSubjectList();

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    boolean deleteSubjectById(String id);
}
