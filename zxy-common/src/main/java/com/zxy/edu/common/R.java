package com.zxy.edu.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 定义具体的数据返回格式
 * @Author: zhangxy
 * @Date: Created in 2019/12/26
 */
@Data
public class R {

    private Boolean success; // 响应是否成功
    private Integer code; // 状态码
    private String message; // 返回信息
    private Map<String, Object> data = new HashMap<>(); // 返回数据，放在键值对中

    private R() {
    }

    /**
     * 操作成功，调用这个方法
     *
     * @return 返回成功的数据
     */
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("操作成功");
        return r;
    }

    /**
     * 操作失败，调用这个方法
     *
     * @return 返回失败的数据
     */
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("操作失败");
        return r;
    }


    // 使用链式编程

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }


    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

}
