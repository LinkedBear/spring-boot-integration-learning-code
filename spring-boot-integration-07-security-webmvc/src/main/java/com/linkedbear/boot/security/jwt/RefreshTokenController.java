package com.linkedbear.boot.security.jwt;

import com.linkedbear.boot.security.entity.SecurityUser;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 基于WebMvc的jwt访问令牌刷新
 */
@RestController
public class RefreshTokenController {
    
    public static final String REFRESH_HEADER = "refreshtoken";
    
    private PrivateKey privateKey;
    private PublicKey publicKey;
    
    public RefreshTokenController() {
        this.privateKey = RsaUtils.getPrivateKey("jwt_rsa");
        this.publicKey = RsaUtils.getPublicKey("jwt_rsa.pub");
    }
    
    @PostMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String refreshJwt = request.getHeader(REFRESH_HEADER);
        if (!StringUtils.hasText(refreshJwt)) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("没有携带刷新令牌！");
            response.getWriter().flush();
            response.getWriter().close();
        }
        // 解析刷新jwt令牌
        try {
            String payloadEncodedJson = JwtUtils.getTokenPayload(refreshJwt, publicKey);
            String userTokenJson = JwtUtils.decodeRefreshToken(payloadEncodedJson);
            SecurityUser user = JsonUtils.parseObject(userTokenJson, SecurityUser.class);
            JwtUtils.writeJwtToken(response, user, privateKey, 30, 3600);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("刷新成功！");
            response.getWriter().flush();
            response.getWriter().close();
        } catch (ExpiredJwtException e) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("刷新令牌已过期！");
            response.getWriter().flush();
            response.getWriter().close();
        } catch (JwtException e) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("刷新令牌解析失败！");
            response.getWriter().flush();
            response.getWriter().close();
        }
    }
}
