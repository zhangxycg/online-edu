package com.zxy.edu.eduservice.handler;

import com.zxy.edu.common.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 统一异常处理类
 * @Author: zhangxy
 * @Date: Created in 2019/12/27
 */
@ControllerAdvice // AOP思想，表示是一个通知或切面，对当前项目中的所有异常做处理
public class GlobalExceptionHandler {

    /**
     * 对所有的异常做相同的处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class) // 处理所有的异常
    @ResponseBody // 向页面响应异常信息
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("出现了异常。。。");
    }
}
