package com.zxy.edu.eduservice.entity.query;

import lombok.Data;

/**
 * @Description: 用来封装查询条件的值
 * @Author: zhangxy
 * @Date: Created in 2019/12/27
 */
@Data
public class QueryTeacher {

    private String name;
    private String level;
    private String beginTime; // 由于页面没有类型的概念，因此时间类型使用String
    private String endTime;
}
