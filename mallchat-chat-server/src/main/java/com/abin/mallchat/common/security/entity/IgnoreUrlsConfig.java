package com.abin.mallchat.common.security.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/2/23 23:32
 * @Description 忽略URL
 */
@Data
@Component
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {

    private List<String> urls = new ArrayList<>(10);

}
