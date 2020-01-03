package com.zxy.edu.eduservice.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 常量类，在服务器启动的时候，读取配置文件application.properties中的配置
 *          使用@Value读取application.properties里的配置内容
 *          用spring的 InitializingBean 的 afterPropertiesSet 来初始化配置信息，
 *          这个方法将在所有的属性被初始化后调用。
 * @Author: zhangxy
 * @Date: Created in 2020/1/3
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${aliyun.oss.file.endpoint}")
    private String endPoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    // 定义常量，为了能够使用
    public static String ENDPOINT;
    public static String KEYID;
    public static String KEYSECRET;
    public static String BUCKETNAME;
    /**
     * 服务器启动的时候，ConstantPropertiesUtil初始化，调用里面的afterPropertiesSet方法，读取配置文件的内容
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endPoint;
        KEYID = keyId;
        KEYSECRET = keySecret;
        BUCKETNAME = bucketName;
    }
}
