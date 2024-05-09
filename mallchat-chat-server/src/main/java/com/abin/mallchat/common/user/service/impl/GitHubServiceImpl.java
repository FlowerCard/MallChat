package com.abin.mallchat.common.user.service.impl;

import cn.hutool.http.*;
import com.abin.mallchat.common.common.domain.dto.GitHubLoginAuthorizeDTO;
import com.abin.mallchat.common.common.domain.properties.GitHubAuthProperties;
import com.abin.mallchat.common.common.domain.vo.response.GitHubLoginAccessTokenParamResp;
import com.abin.mallchat.common.common.exception.BusinessException;
import com.abin.mallchat.common.user.domain.dto.GitHubAuthRespDTO;
import com.abin.mallchat.common.user.domain.dto.GitHubUserDTO;
import com.abin.mallchat.common.user.domain.entity.User;
import com.abin.mallchat.common.user.service.GitHubService;
import com.abin.mallchat.common.user.service.LoginService;
import com.abin.mallchat.common.user.service.UserService;
import com.abin.mallchat.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/3/18 15:14
 */
@Slf4j
@Service
@AllArgsConstructor
public class GitHubServiceImpl implements GitHubService {

    private final UserService userService;

    private final LoginService loginService;

    private final GitHubAuthProperties gitHubAuthProperties;


    @Override
    public String loginWithGitHub(GitHubLoginAuthorizeDTO authorizeDTO) {
        log.info("GitHub授权回调成功 -> : {}", JsonUtils.toStr(authorizeDTO));
        // 拼接获取 GitHub 授权 token 参数
        GitHubLoginAccessTokenParamResp accessTokenParam = generateAuthTokenParam(authorizeDTO.getCode());
        log.info("生成获取 GitHub 授权 token 参数 accessTokenParam -> {}", JsonUtils.toStr(accessTokenParam));
        // 发送请求获取token
        HttpRequest gitHubTokenAuthReq = HttpUtil.createPost(accessTokenParam.getAccessTokenUrl());
        // post 请求
        gitHubTokenAuthReq.method(Method.POST);
        // 请求头 重写为 接受 json 格式
        gitHubTokenAuthReq.header(Header.ACCEPT, MediaType.APPLICATION_JSON_VALUE, true);
        // 请求体
        gitHubTokenAuthReq.body(JsonUtils.toStr(accessTokenParam));
        // 发送请求
        HttpResponse response = gitHubTokenAuthReq.execute();
        if (!response.isOk()) {
            log.error("获取 GitHub 授权 token 失败 -> {}", response);
            throw new BusinessException("获取 GitHub 授权 token 失败");
        }
        GitHubAuthRespDTO authRespDTO = JsonUtils.toObj(response.body(), GitHubAuthRespDTO.class);

        // 发送获取用户信息的请求
        HttpResponse accessUserResp = HttpUtil.createGet(gitHubAuthProperties.getAccessUserUrl())
                .header(Header.AUTHORIZATION, authRespDTO.getToken_type() + " " + authRespDTO.getAccess_token())
                .execute();
        // 判断访问是否成功
        if (!accessUserResp.isOk()) {
            log.error("获取 GitHub 用户信息失败 -> {}", accessUserResp);
            throw new BusinessException("获取 GitHub 用户信息失败");
        }
        GitHubUserDTO userDTO = JsonUtils.toObj(accessUserResp.body(), GitHubUserDTO.class);

        User user = userService.getUserInfoByGitHubId(userDTO.getId());
        if (Objects.isNull(user)) {
            // user对象为空时，创建用户
            user = User.builder()
                    .name(userDTO.getLogin())
                    .openId("")
                    .avatar(userDTO.getAvatarUrl())
                    .githubLoginName(userDTO.getLogin())
                    .githubId(userDTO.getId())
                    .githubHtmlUrl(userDTO.getHtmlUrl())
                    .githubLoginInfo(userDTO)
                    .build();
            userService.register(user);
        }
        // 登录
        return loginService.login(user.getId());
    }

    /**
     * 生成获取 GitHub 授权 token 参数
     *
     * @param code 授权码
     * @return 获取 GitHub 授权 token 参数
     */
    private GitHubLoginAccessTokenParamResp generateAuthTokenParam(String code) {
        String accessTokenUrl = gitHubAuthProperties.getAccessTokenUrl();
        return GitHubLoginAccessTokenParamResp.builder()
                .accessTokenUrl(accessTokenUrl)
                .code(code)
                .client_id(gitHubAuthProperties.getClientId())
                .client_secret(gitHubAuthProperties.getClientSecret()).build();
    }
}
