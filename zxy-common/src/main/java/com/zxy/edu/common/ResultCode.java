package com.zxy.edu.common;

/**
 * @Description: 定义返回数据使用的状态码
 * @Author: zhangxy
 * @Date: Created in 2019/12/26
 */
public interface ResultCode {

    int SUCCESS = 20000; // 成功的状态码
    int ERROR = 20001;   // 失败的状态码
    int AUTH =30000;     // 没有操作权限的状态码
}
