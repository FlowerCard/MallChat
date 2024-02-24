package com.abin.mallchat.common.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/2/24 11:33
 * @Description 登录类型枚举
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnums {

    /**
     * 用户名密码登录 todo flowercard doing
     */
    PASSWORD("password", "用户名密码登录"),
    /**
     * 手机号登录 todo flowercard
     */
    PHONE("phone", "手机号登录"),
    /**
     * 邮箱登录 todo flowercard
     */
    EMAIL("email", "邮箱登录"),
    /**
     * 微信登录 todo flowercard 等待转化
     */
    WECHAT("wechat", "微信登录"),
    /**
     * QQ登录 todo flowercard
     */
    QQ("qq", "QQ登录"),
    /**
     * 微博登录 todo flowercard
     */
    WEIBO("weibo", "微博登录"),
    /**
     * 钉钉登录 todo flowercard
     */
    DINGDING("dingding", "钉钉登录"),
    /**
     * 支付宝登录 todo flowercard
     */
    ALIPAY("alipay", "支付宝登录"),
    /**
     * 小程序登录 todo flowercard
     */
    MINIAPP("miniapp", "小程序登录"),
    /**
     * GitHub登录 todo flowercard doing
     */
    GITHUB("github", "GitHub登录"),
    ;
    private final String type;
    private final String desc;

}
