package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class WebController {
    @Autowired
    EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {

        Emp e = empService.login(emp);

        // 查找到用户名密码下有对应的用户
        if (e != null) {

            // 1.创建Map数组将数据以键值对key=value的形式传入JWT载荷，将其变为json数据key:value
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id", emp.getId());
            claims.put("name", emp.getName());
            claims.put("username", emp.getUsername());

            // 2.调用生成jwt秘钥token的方法,将用户的基本信息封装到JWT秘钥
            String token = JwtUtils.generateJwt(claims);
            log.info("==================== 登录 token:{}====================", token);

            return Result.success(token);
        } else {
            return Result.error("用户名或密码错误！");
        }
    }

}
