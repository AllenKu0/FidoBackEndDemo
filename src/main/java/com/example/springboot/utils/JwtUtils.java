package com.example.springboot.utils;


import com.example.springboot.constant.SecurityConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.xml.bind.DatatypeConverter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private static final byte[] secretKey = DatatypeConverter.parseBase64Binary(SecurityConstants.JWT_SECRET_KEY);

    private JwtUtils() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }

    /**
     * 根据用户名和用户角色生成 token
     *
     * @param email   信箱
     *                * @param isRemember 是否记住我
     * @return 返回生成的 token
     */

    //令人痛苦的東西
    //註冊聲明參數 (建議但不強制使用)
    //iss (Issuer) - jwt簽發者
    //sub (Subject) - jwt所面向的用戶
    //aud (Audience) - 接收jwt的一方
    //exp (Expiration Time) - jwt的過期時間，這個過期時間必須要大於簽發時間
    //nbf (Not Before) - 定義在什麼時間之前，該jwt都是不可用的
    //iat (Issued At) - jwt的簽發時間
    //jti (JWT ID) - jwt的唯一身份標識，主要用來作為一次性token,從而迴避重放攻擊
    public static String generateToken(String email, boolean isRemember) {
        List<String> roles = Collections.singletonList("ROLE_USER");
        byte[] jwtSecretKey = DatatypeConverter.parseBase64Binary(SecurityConstants.JWT_SECRET_KEY);
        // 过期时间
        long expiration = isRemember ? SecurityConstants.EXPIRATION_REMEMBER_TIME : SecurityConstants.EXPIRATION_TIME;
        // 生成 token
        String token = Jwts.builder()
                // 生成签证信息
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .signWith(Keys.hmacShaKeyFor(jwtSecretKey), SignatureAlgorithm.HS256)
                .setSubject(email)
                .claim(SecurityConstants.TOKEN_ROLE_CLAIM, roles)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setIssuedAt(new Date())
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                // 设置有效时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
        return token;
    }



    /**
     * 验证 token 是否有效
     *
     * <p>
     * 如果解析失败，说明 token 是无效的
     *
     * @param token token 信息
     * @return 如果返回 true，说明 token 有效
     */

    public static boolean validateToken(String token) {
        try {
            getTokenBody(token);
            logger.warn("validateToken"+token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.warn("Request to parse expired JWT : {} failed : {}", token, e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.warn("Request to parse unsupported JWT : {} failed : {}", token, e.getMessage());
        } catch (MalformedJwtException e) {
            logger.warn("Request to parse invalid JWT : {} failed : {}", token, e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.warn("Request to parse empty or null JWT : {} failed : {}", token, e.getMessage());
        }
        return false;
    }

    /**
     * 根据 token 获取用户认证信息
     *
     * @param token token 信息
     * @return 返回用户认证信息
     */
    public static Authentication getAuthentication(String token) {
        Claims claims = getTokenBody(token);
        // 获取用户角色字符串
        List<String> roles = (List<String>)claims.get(SecurityConstants.TOKEN_ROLE_CLAIM);
        List<SimpleGrantedAuthority> authorities =
                Objects.isNull(roles) ? Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")) :
                        roles.stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());
        // 获取用户名
        String userName = claims.getSubject();

        return new UsernamePasswordAuthenticationToken(userName, token, authorities);

    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

}

