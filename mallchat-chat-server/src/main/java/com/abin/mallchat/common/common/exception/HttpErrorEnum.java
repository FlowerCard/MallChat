package com.abin.mallchat.common.common.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.abin.mallchat.common.common.domain.vo.response.ApiResult;
import com.google.common.base.Charsets;

import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: 业务校验异常码
 * Author: <a href="https://github.com/zongzibinbin">abin</a>
 * Date: 2023-03-26
 */
@AllArgsConstructor
@Getter
public enum HttpErrorEnum implements ErrorEnum {
    ACCESS_DENIED(401, "登录失效，请重新登录"),
    FORBIDDEN(403, "无权访问"),
    ;
    private Integer httpCode;
    private String msg;

    @Override
    public Integer getErrorCode() {
        return httpCode;
    }

    @Override
    public String getErrorMsg() {
        return msg;
    }

    public void sendHttpError(HttpServletResponse response) throws IOException {
        response.setStatus(this.getErrorCode());
        ApiResult responseData = ApiResult.fail(this);
        response.setContentType(ContentType.JSON.toString(Charsets.UTF_8));
        response.getWriter().write(JSONUtil.toJsonStr(responseData));
    }
}
