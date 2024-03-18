package com.abin.mallchat.common.security.service.impl;

import com.abin.mallchat.common.common.domain.vo.response.UserDetailVo;
import com.abin.mallchat.common.security.enums.ClientTypeEnum;
import com.abin.mallchat.common.security.service.AbstactLoginUserService;
import com.abin.mallchat.common.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/3/18 16:04
 * @Description 密码登陆用户服务
 */
@Slf4j
@Service
public class PasswordLoginUserService extends AbstactLoginUserService {
    public PasswordLoginUserService(PasswordEncoder passwordEncoder,
                                    UserService userService) {
        super(passwordEncoder, userService);
    }

    @Override
    public ClientTypeEnum client() {
        return ClientTypeEnum.PASSWORD;
    }

    @Override
    public UserDetailVo login(String username, String password, HttpServletRequest request) throws OAuth2AuthenticationException {
        return super.login(username, password, request);
    }

    @Override
    public UserDetailVo loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public void updateUserInfo(UserDetailVo userDetailVo) {

    }
}
