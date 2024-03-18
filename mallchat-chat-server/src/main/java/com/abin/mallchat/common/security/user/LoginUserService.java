package com.abin.mallchat.common.security.user;

import com.abin.mallchat.common.common.domain.vo.response.UserDetailVo;
import com.abin.mallchat.common.security.enums.ClientTypeEnum;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/3/18 11:16
 * @Description 用户登陆接口
 */
public interface LoginUserService extends UserDetailsService {

    /**
     * @return 登陆端类型
     */
    ClientTypeEnum client();

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     * @param request  请求
     * @return 用户详情
     */
    UserDetailVo login(String username, String password, HttpServletRequest request) throws OAuth2AuthenticationException;

    /**
     * 根据用户名加载用户详情
     *
     * @param username 用户名
     * @return 用户详情
     */
    @Override
    UserDetailVo loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * 根据用户名加载用户详情
     *
     * @param username     用户名
     * @param enterpriseId 企业ID
     * @return 用户详情
     */
    default UserDetailVo loadUserByUsername(String username, Long enterpriseId) throws UsernameNotFoundException {
        return loadUserByUsername(username);
    }

    void updateUserInfo(UserDetailVo userDetailVo);


}
