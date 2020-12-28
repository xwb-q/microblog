package com.cloudcommons.springcloud.util;

import com.cloudcommons.springcloud.entities.BgUser;
import com.cloudcommons.springcloud.entities.User;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

public class TokenManager {

    /**
     * 签名秘钥
     */
    public static final String SECRET = "admin";



    /**
     * 生成token
     *
     * @param id 传入userName
     * @return
     */
    public static String createJwtToken(String id,Object o) {
        User user = null;
        if(o instanceof User)
             user = (User)o;
        String issuer = "www.xiong.com";
        String subject = "1257181026@qq.com";
        long ttlMillis = 60*1000*30;//  6s

        // 签名算法 ，将对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

//        HashMap<String,Object> map = new HashMap<>();
//        map.put(id, user);

        // 通过秘钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setId(id)
                .setExpiration(new Date(System.currentTimeMillis() + ttlMillis))
                .setIssuedAt(now)
                .setSubject(subject)
                .claim("username",user.getUsername())
                .claim("password",user.getPassword())
                .setIssuer(issuer)//设定签发者
//                .setClaims(map)//将用户的信息放进去
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }


    // Sample method to validate and read the JWT
    public static Claims parseJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))//根据密钥进行解密
                .parseClaimsJws(jwt);
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))//根据密钥进行解密
                .parseClaimsJws(jwt).getBody();
        System.out.println(claims.get("username"));
        System.out.println(claims.get("password"));
//        HashMap user = claims.get("user", String.class);
//        System.out.println();

//        System.out.println(claims.ge+"");
        return claims;
    }


    //查找token
    public static boolean findJwtToken(RedisOps redisOps,String token){
        String username = redisOps.get(token);
//        System.out.println(username);
        return username!=null;
    }

}
