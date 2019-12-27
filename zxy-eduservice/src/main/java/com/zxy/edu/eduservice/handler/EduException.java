package com.zxy.edu.eduservice.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 自定义异常类
 * @Author: zhangxy
 * @Date: Created in 2019/12/27
 */
@Data
@NoArgsConstructor // 无参构造
@AllArgsConstructor // 有参构造
public class EduException extends RuntimeException {

    private Integer code; // 异常状态码
    private String message; // 异常信息

}
