package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
public class FileController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

     /*本地文件上传方法
    @PostMapping
    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
        log.info("==================== 文件上传{},{},{} ====================", username, age, image);

        // 获取原始的文件名 1.jpg
        String originalFilename = image.getOriginalFilename();

        // 构造唯一的文件名 -uuid（通用唯一识别码） 93cd7a58-faa6-4559-b5d0-a259fdcab448.jpg (采用时间戳的方式也有可能导致相同时间文件重复)
        int index = originalFilename.lastIndexOf("."); // 原始文件名最后后缀.的位置
        String extName = originalFilename.substring(index); // 截取原始文件名的后缀名 .jpg
        String newFileName = UUID.randomUUID().toString() + extName; // 拼接新的文件名

        // 存储文件名在服务器磁盘目录当中 E:\image
        image.transferTo(new File("E:\\image\\" + newFileName));

        return Result.success();
    }*/

    // 文件上传到OSS阿里云服务器接口
    @PostMapping
    public Result upload(MultipartFile image) throws Exception { // MultipartFile是Spring用来获取上传文件信息的封装类
        log.info("==================== 文件上传，原始文件名{} ====================", image.getOriginalFilename());

        // 调用阿里云OSS工具类进行文件上传
        String url = aliOSSUtils.upload(image);
        log.info("==================== 文件上传，上传服务器文件名{} ====================", url);

        return Result.success(url);
    }
}
