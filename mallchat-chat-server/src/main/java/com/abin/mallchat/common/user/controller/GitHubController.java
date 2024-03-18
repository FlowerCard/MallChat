package com.abin.mallchat.common.user.controller;

import cn.hutool.core.lang.UUID;
import com.abin.mallchat.common.common.domain.dto.GitHubLoginAuthorizeDTO;
import com.abin.mallchat.common.common.domain.properties.GitHubAuthProperties;
import com.abin.mallchat.common.common.domain.vo.response.ApiResult;
import com.abin.mallchat.common.common.domain.vo.response.GitHubLoginAccessTokenParamResp;
import com.abin.mallchat.utils.JsonUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/2/18 15:41
 * GitHub授权登录
 */
@Slf4j
@RestController
@RequestMapping("/github/public")
public class GitHubController {

    @Resource
    private GitHubAuthProperties gitHubAuthProperties;

    /**
     * 获取GitHub授权登录地址
     */
    @SneakyThrows
    @GetMapping("/authorize")
    public void getGitHubAuthorizeUrl(HttpServletResponse response) {
        String authorizeUrl = gitHubAuthProperties.getAuthorizeUrl();
        String redirectUrl = authorizeUrl.concat("?client_id=").concat(gitHubAuthProperties.getClientId())
                .concat("&state=").concat(UUID.fastUUID().toString().replace("-", "")).toString();
        log.info("redirectUrl -> {}", redirectUrl);
        response.sendRedirect(redirectUrl);
    }

    /**
     * 获取GitHub AccessToken URL
     * @param authorizeDTO github 授权响应内容
     * @return accessToken Url
     */
    @PostMapping("/accessToken")
    public ApiResult<GitHubLoginAccessTokenParamResp> githubAccessToken(@RequestBody GitHubLoginAuthorizeDTO authorizeDTO) {
        String accessTokenUrl = gitHubAuthProperties.getAccessTokenUrl();
        GitHubLoginAccessTokenParamResp paramResp = GitHubLoginAccessTokenParamResp.builder()
                .accessTokenUrl(accessTokenUrl)
                .code(authorizeDTO.getCode())
                .clientId(gitHubAuthProperties.getClientId())
                .clientSecret(gitHubAuthProperties.getClientSecret()).build();
        return ApiResult.success(paramResp);
    }

    /**
     * GitHub授权登录回调
     * @param authorizeDTO 授权DTO
     */
    @SneakyThrows
    @RequestMapping("/callBack")
    public ApiResult<GitHubLoginAuthorizeDTO> AccessGithubLogin(GitHubLoginAuthorizeDTO authorizeDTO) {
        log.info("authorizeDTO -> {}", JsonUtils.toStr(authorizeDTO));
        return ApiResult.success(authorizeDTO);
    }

}
