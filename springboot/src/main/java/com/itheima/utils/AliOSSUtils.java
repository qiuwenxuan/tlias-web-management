package com.itheima.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */
@Component
public class AliOSSUtils {
//    @Value("${aliyun.oss.endpoint}")
//    private String endpoint; //    对象存储https https://oss-cn-nanjing.aliyuncs.com
//    @Value("${aliyun.oss.accessKeyId}")
//    private String accessKeyId; //    对象存储accessKeyId LTAI5t75aho9HZ3Yhpq6ECHU
//    @Value("${aliyun.oss.accessKeySecret}")
//    private String accessKeySecret; //    对象存储accessKeySecret FOx618MZxQPl9Ixs8dA7IDDLLIXhpc
//    @Value("${aliyun.oss.bucketName}")
//    private String bucketName; //    对象存储bucketName web-tlias-xuanchen
    @Autowired
    AliOProperties aliOProperties;

    /**
     * 实现上传图片到OSS
     */
    public String upload(MultipartFile file) throws IOException {
        String endpoint = aliOProperties.getEndpoint();
        String accessKeyId = aliOProperties.getAccessKeyId();
        String accessKeySecret = aliOProperties.getAccessKeySecret();
        String bucketName = aliOProperties.getBucketName();
        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 创建新的文件名字，避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf(".")); // UUID+文件名后缀

        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);

        //拼接服务器文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;

        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }

}
