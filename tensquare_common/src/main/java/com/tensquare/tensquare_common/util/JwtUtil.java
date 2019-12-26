package com.tensquare.tensquare_common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 * jwt工具类
 */
@ConfigurationProperties("jwt.config")
public class JwtUtil {

    private String key;

    private long ttl;//一个小时

    /**
     * 生成jwt
     * @param id
     * @param subject
     * @param roles
     * @return
     */
    public String createJWT(String id, String subject, String roles) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                //设置签发时间
                .setIssuedAt(now)
                //设置签名密钥
                .signWith(SignatureAlgorithm.HS256, key)
                //自定义声明
                .claim("roles", roles);
        if (ttl > 0) {
            //设置过期时间(并不希望签发的token是永久生效的)
            builder.setExpiration(new Date(nowMillis + ttl));
        }
        return builder.compact();
    }

    /**
     * 解析JWT
     * @param jwtStr
     * @return
     */
    public Claims parseJWT(String jwtStr) {
        Claims claims = Jwts
                            .parser()
                            .setSigningKey(key)
                            .parseClaimsJws(jwtStr)
                            .getBody();
        //System.out.println(claims.getId());
        return claims;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

}
