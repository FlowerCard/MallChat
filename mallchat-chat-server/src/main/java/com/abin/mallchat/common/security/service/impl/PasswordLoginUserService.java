package com.abin.mallchat.common.security.service.impl;

import com.abin.mallchat.common.common.domain.vo.response.UserDetailVo;
import com.abin.mallchat.common.common.exception.HttpErrorEnum;
import com.abin.mallchat.common.common.utils.AssertUtil;
import com.abin.mallchat.common.security.enums.ClientTypeEnum;
import com.abin.mallchat.common.security.service.AbstactLoginUserService;
import com.abin.mallchat.common.user.domain.entity.User;
import com.abin.mallchat.common.user.service.UserService;
import com.abin.mallchat.common.user.service.adapter.UserAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public UserDetailVo loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserInfo(username);
        AssertUtil.isNotEmpty(user, HttpErrorEnum.UNKNOWN_USER);
        UserDetailVo detailVo = UserAdapter.buildUserDetailVo(user);
        // todo flowercard 后期再改role_code，先把功能做好，零零碎碎搞了好久
        return detailVo;
    }

    @Override
    public void updateUserInfo(UserDetailVo userDetailVo) {

    }
}
