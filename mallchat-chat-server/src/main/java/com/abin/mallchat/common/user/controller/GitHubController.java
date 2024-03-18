package com.abin.mallchat.common.user.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.abin.mallchat.common.common.constant.RedisKey;
import com.abin.mallchat.common.common.domain.dto.GitHubLoginAuthorizeDTO;
import com.abin.mallchat.common.common.domain.properties.GitHubAuthProperties;
import com.abin.mallchat.common.common.domain.vo.response.ApiResult;
import com.abin.mallchat.common.common.exception.HttpErrorEnum;
import com.abin.mallchat.common.user.domain.dto.GitHubUserDTO;
import com.abin.mallchat.common.user.service.GitHubService;
import com.abin.mallchat.utils.JsonUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Resource
    private StringRedisTemplate stringRedisTemplate;

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
     */
    @RequestMapping("/callBack")
    public ApiResult<String> accessGithubLogin(GitHubLoginAuthorizeDTO authorizeDTO) {
        log.info("authorizeDTO -> {}", JsonUtils.toStr(authorizeDTO));
        return ApiResult.success(gitHubService.githubLogin(authorizeDTO));
    }

    /**
     * 获取GitHub授权用户信息
     *
     * @return GitHub授权用户信息
     */
    @GetMapping("/getAccessUser")
    public ApiResult<GitHubUserDTO> getAccessUser(@RequestParam("code") String code) {
        String githubLoginInfo = stringRedisTemplate.opsForValue().get(RedisKey.getKey(RedisKey.GITHUB_LOGIN_INFO, code));
        if (StrUtil.isBlank(githubLoginInfo)) {
            return ApiResult.fail(HttpErrorEnum.FORBIDDEN);
        }
        return ApiResult.success(JsonUtils.toObj(githubLoginInfo, GitHubUserDTO.class));

    }

}
