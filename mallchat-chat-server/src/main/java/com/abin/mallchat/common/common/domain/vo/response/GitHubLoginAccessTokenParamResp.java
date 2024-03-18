package com.abin.mallchat.common.common.domain.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/2/18 17:07
 * GitHub Access Token 访问参数响应
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GitHubLoginAccessTokenParamResp {

    /**
     * GitHub OAuth App AuthorizeUrl
     */
    private String accessTokenUrl;

    /**
     * GitHub OAuth App ClientId
     */
    private String client_id;

    /**
     * GitHub OAuth App ClientSecret
     */
    private String client_secret;

    /**
     * 用于代码交换的证明密钥
     */
    private String code;

}
