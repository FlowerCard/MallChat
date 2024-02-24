package com.abin.mallchat.common.common.utils;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * Description: jwt的token生成与解析
 * Author: <a href="https://github.com/zongzibinbin">abin</a>
 * Date: 2023-04-03
 */
@Slf4j
@Component
public class JwtUtils {

    /**
     * token秘钥，请勿泄露，请勿随便修改
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * token请求头
     */
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    /**
     * token前缀
     */
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * token过期时间
     */
    @Value("${jwt.expiration}")
    private String expiration;

    private static final String UID_CLAIM = "uid";
    private static final String CREATE_TIME = "createTime";

    /**
     * JWT生成Token.<br/>
     * <p>
     * JWT构成: header, payload, signature
     */
    public String createToken(Long uid) {
        // build token
        String token = JWT.create()
                .withClaim(UID_CLAIM, uid) // 只存一个uid信息，其他的自己去redis查
                .withClaim(CREATE_TIME, new Date())
                .sign(Algorithm.HMAC256(secret)); // signature
        return token;
    }

    /**
     * 生成token
     *
     * @param claims 自定义的负载
     */
    public String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + Long.parseLong(expiration) * 1000);
    }

    /**
     * 解密Token
     *
     * @param token
     * @return
     */
    public Map<String, Claim> verifyToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (Exception e) {
            log.error("decode error,token:{}", token, e);
        }
        return null;
    }

    /**
     * 校验token是否正确
     *
     * @param authToken   token
     * @param userDetails 用户
     * @return 是否正确
     */
    public boolean verifyToken(String authToken, UserDetails userDetails) {
        String nameFromToken = getUserNameFromToken(authToken);
        return StrUtil.equals(nameFromToken, userDetails.getUsername())
                && !isTokenExpired(authToken);
    }

    /**
     * 判断token是否已经失效
     *
     * @param authToken token
     * @return 是否失效
     */
    private boolean isTokenExpired(String authToken) {
        Date expiredDate = getExpiredDateFromToken(authToken);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     *
     * @param authToken token
     * @return 过期时间
     */
    private Date getExpiredDateFromToken(String authToken) {
        Claims claims = getClaimsFromToken(authToken);
        return claims.getExpiration();
    }


    /**
     * 根据Token获取uid
     *
     * @param token
     * @return uid
     */
    public Long getUidOrNull(String token) {
        return Optional.ofNullable(verifyToken(token))
                .map(map -> map.get(UID_CLAIM))
                .map(Claim::asLong)
                .orElse(null);
    }

    /**
     * 从token中获取用户名
     *
     * @param authToken token
     * @return 用户名
     */
    public String getUserNameFromToken(String authToken) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(authToken);
            username = claims.getSubject();
        } catch (Exception e) {
            log.error("getUserNameFromToken error", e);
        }
        return username;
    }

    /**
     * 从token中获取 JWT 中的负载
     *
     * @param authToken token
     * @return 负载
     */
    private Claims getClaimsFromToken(String authToken) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJwt(authToken)
                    .getBody();
        } catch (Exception e) {
            log.error("getClaimsFromToken error", e);
        }
        return claims;
    }
}
