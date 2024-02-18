package com.abin.mallchat.common.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/2/18 16:24
 * @Description GitHub登录授权
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitHubLoginAuthorizeDTO {

    /**
     *
     * GitHub 会使用代码参数中的临时 code 以及你在上一步的 state 参数中提供的状态重定向回你的站点
     * 用于代码交换的证明密钥
     */
    private String code;

    /**
     * 不可猜测的随机字符串。 它用于防止跨站请求伪造攻击
     */
    private String state;

}
