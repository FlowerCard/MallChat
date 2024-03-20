package com.abin.mallchat.common.user.service;

import com.abin.mallchat.common.common.domain.dto.GitHubLoginAuthorizeDTO;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/3/18 15:12
 * @Description GitHub登录
 */
public interface GitHubService {

    /**
     * GitHub登录信息
     *
     * @param authorizeDTO 认证信息
     * @return 登录信息code
     */
    String githubLoginInfo(GitHubLoginAuthorizeDTO authorizeDTO);

    /**
     * 通过GitHub登录
     *
     * @param code 登录信息code
     * @return token
     */
    String loginWithGitHub(String code);

}
