package com.zxy.edu.eduservice.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description:
 * @Author: zhangxy
 * @Date: Created in 2019/12/27
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 在mp执行添加操作的时候运行(会将createTime和updateTime加入到数据库中）
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }

    /**
     * 在mp执行修改操作的时候运行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
}
