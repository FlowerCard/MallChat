package com.abin.mallchat.common.common.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.abin.mallchat.common.common.domain.vo.response.ApiResult;
import com.abin.mallchat.common.common.exception.HttpErrorEnum;
import com.abin.mallchat.utils.JsonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/2/23 23:26
 * @Description 当未登录或者token失效访问接口时，自定义的返回结果
 */
@Slf4j
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(
            JsonUtils.toStr(ApiResult.fail(HttpErrorEnum.ACCESS_DENIED.getErrorCode(), authException.getMessage())));
        response.getWriter().flush();

    }
}
