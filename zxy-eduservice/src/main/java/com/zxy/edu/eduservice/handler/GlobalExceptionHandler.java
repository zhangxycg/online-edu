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

    /**
     * 对特定的异常进行处理（如果有更多的异常，继续添加需要处理的异常）
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("0不能作为除数，出现了异常。。。");
    }
}
