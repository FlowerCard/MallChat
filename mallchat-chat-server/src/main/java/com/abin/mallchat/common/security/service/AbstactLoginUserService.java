package com.abin.mallchat.common.security.service;

import com.abin.mallchat.common.common.domain.vo.response.UserDetailVo;
import com.abin.mallchat.common.security.user.LoginUserService;
import com.abin.mallchat.common.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/3/18 14:09
 * @Description 登陆用户服务抽象类
 */
@Slf4j
@AllArgsConstructor
public abstract class AbstactLoginUserService implements LoginUserService {

    protected final PasswordEncoder passwordEncoder;

    protected final UserService userService;

    @Override
    public UserDetailVo login(String username, String password, HttpServletRequest request) throws OAuth2AuthenticationException {
        UserDetailVo detailVo;
        try {
            detailVo = loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            log.error("当前用户登陆异常！", e);
            throw new OAuth2AuthenticationException("当前用户登录异常");
        }
        // 判断用户是否为空
        if (null == detailVo) {
            throw new OAuth2AuthenticationException("未知用户登录");
        }

        // 检测用户密码是否正确
        validPassword(password, detailVo);
        detailVo.setClientType(client().getType());
        return detailVo;
    }

    /**
     * 校验用户密码
     *
     * @param password 用户输入密码
     * @param detailVo 用户信息
     */
    protected void validPassword(String password, UserDetailVo detailVo) {
        if (!passwordEncoder.matches(password, detailVo.getPassword())) {
            throw new OAuth2AuthenticationException("用户名或密码错误");
        }
    }
}
