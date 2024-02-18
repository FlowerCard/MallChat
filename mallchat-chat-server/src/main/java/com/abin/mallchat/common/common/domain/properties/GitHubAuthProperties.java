package com.abin.mallchat.common.common.domain.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/2/18 16:32
 */
@Data
@Component
@ConfigurationProperties(prefix = "mallchat.github")
public class GitHubAuthProperties {

    /**
     * GitHub OAuth App ClientId
     */
    private String clientId;

    /**
     * GitHub OAuth App ClientSecret
     */
    private String clientSecret;

    /**
     * GitHub OAuth App Callback
     * 这个暂时没啥用，因为GitHub的回调地址是固定的
     */
    private String callback;

    /**
     * GitHub OAuth App AuthorizeUrl
     */
    private String authorizeUrl;

    /**
     * GitHub OAuth App AccessTokenUrl
     */
    private String accessTokenUrl;

}
