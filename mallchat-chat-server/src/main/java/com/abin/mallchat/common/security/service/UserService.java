package com.abin.mallchat.common.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/2/19 14:33
 * @Description 用户服务
 */
@Slf4j
@Service
public class UserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

}
