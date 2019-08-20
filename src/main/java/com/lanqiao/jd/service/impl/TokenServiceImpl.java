package com.lanqiao.jd.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lanqiao.jd.entity.User;
import com.lanqiao.jd.service.TokenService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author 刘佳昇
 * @Date 2019/8/14 15:26
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String getToken(User user) {
        String token = "";
        token = JWT.create().withAudience(""+user.getUserId())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    public String getMD5(String str) throws Exception {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new Exception("MD5加密出现错误");
        }
    }
}
