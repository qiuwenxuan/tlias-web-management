package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

//@SpringBootTest
class TliasWebManagementApplicationTests {
    @Test
    public void uuidTest() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(UUID.randomUUID());
        }
    }

    //    生成JWT令牌测试方法
    @Test
    public void testGenJwt() {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "Tom");

        // 生成jwt令牌
        String jwt = Jwts.builder() //jwt=eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiVG9tIiwiaWQiOjEsImV4cCI6MTcxODc4OTUwMX0.pr4ktGHdBj5nRXnTgivJM_b9tkGqDwzIuAGPpITfw4Y
                .signWith(SignatureAlgorithm.HS256, "itheima") // 设置加密算法和秘钥
                .setClaims(claims) // 在载荷部分自定义map形式的存储数据 key:value
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置秘钥的有效时间为当前时间往后1h(单位毫秒ms)
                .compact(); // 生成秘钥返回String类型的jwt令牌
        System.out.println(jwt);
    }

    //    解析JWT令牌
    @Test
    public void testParseJwt() {
        Claims claims = Jwts.parser() // claims={name=Tom, id=1, exp=1718789501}
                .setSigningKey("itheima") // 设置解析秘钥
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiVG9tIiwiaWQiOjEsImV4cCI6MTcxODc4OTUwMX0.pr4ktGHdBj5nRXnTgivJM_b9tkGqDwzIuAGPpITfw4Y") // 解析jwt字符串
                .getBody(); // 获取jwt载荷体

        System.out.println(claims);
    }
}

