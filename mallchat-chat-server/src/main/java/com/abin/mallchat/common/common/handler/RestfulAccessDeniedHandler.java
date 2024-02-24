package com.abin.mallchat.common.common.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.abin.mallchat.common.common.domain.vo.response.ApiResult;
import com.abin.mallchat.common.common.exception.HttpErrorEnum;
import com.abin.mallchat.utils.JsonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/2/23 23:23
 * @Description 当访问接口没有权限时，自定义的返回结果
 */
@Slf4j
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JsonUtils.toStr(ApiResult.fail(HttpErrorEnum.FORBIDDEN)));
        response.getWriter().flush();

    }
}
