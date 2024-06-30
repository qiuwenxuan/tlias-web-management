package com.itheima.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云 OSS 配置项封装类
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")  //自动注入配置信息
public class AliOProperties {
    private String endpoint;
    private String bucketName;
}
