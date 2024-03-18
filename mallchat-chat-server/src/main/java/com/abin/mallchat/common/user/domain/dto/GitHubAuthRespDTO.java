package com.abin.mallchat.common.user.domain.dto;

import lombok.Data;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/3/18 15:50
 */
@Data
public class GitHubAuthRespDTO {

    /**
     * 访问token
     */
    private String access_token;

    /**
     * 访问限制
     */
    private String scope;

    /**
     * token类型
     */
    private String token_type;

}
