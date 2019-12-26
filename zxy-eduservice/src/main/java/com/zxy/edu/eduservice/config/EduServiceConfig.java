package com.zxy.edu.eduservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:
 * @Author: zhangxy
 * @Date: Created in 2019/12/26
 */

@EnableTransactionManagement // 事务
@Configuration // 标明是一个配置类
@MapperScan("com.zxy.edu.eduservice.mapper")
public class EduServiceConfig {
}