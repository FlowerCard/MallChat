package com.abin.mallchat.common.user.controller;

import cn.hutool.core.lang.UUID;
import com.abin.mallchat.common.common.domain.dto.GitHubLoginAuthorizeDTO;
import com.abin.mallchat.common.common.domain.properties.GitHubAuthProperties;
import com.abin.mallchat.common.common.domain.vo.response.ApiResult;
import com.abin.mallchat.common.user.service.GitHubService;
import com.abin.mallchat.utils.JsonUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @Resource
    private GitHubService gitHubService;

    /**
     * 获取GitHub授权登录地址
     */
    @SneakyThrows
    @GetMapping("/authorize")
    public String getGitHubAuthorizeUrl() {
        String authorizeUrl = gitHubAuthProperties.getAuthorizeUrl();
        String redirectUrl = authorizeUrl.concat("?client_id=").concat(gitHubAuthProperties.getClientId())
                .concat("&state=").concat(UUID.fastUUID().toString().replace("-", "")).toString();
        log.info("redirectUrl -> {}", redirectUrl);
        return redirectUrl;
    }

    /**
     * GitHub授权登录回调
     * @param authorizeDTO 授权DTO
     * @return 结果code
     */
    @RequestMapping("/callBack")
    public ApiResult<String> accessGithubLogin(GitHubLoginAuthorizeDTO authorizeDTO) {
        log.info("authorizeDTO -> {}", JsonUtils.toStr(authorizeDTO));
        return ApiResult.success(gitHubService.githubLoginInfo(authorizeDTO));
    }

    /**
     * 通过GitHub登录
     *
     * @return token
     */
    @GetMapping("/loginWithGitHub")
    public ApiResult<String> loginWithGitHub(@RequestParam("code") String code) {
        return ApiResult.success(gitHubService.loginWithGitHub(code));
    }

}
