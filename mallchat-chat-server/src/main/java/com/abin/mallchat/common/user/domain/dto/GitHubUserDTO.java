package com.abin.mallchat.common.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/3/18 15:35
 * @Description GitHub用户信息
 */
@Data
public class GitHubUserDTO {

    /**
     * GitHub 用户名
     */
    @JsonProperty("login")
    private String login;

    /**
     * GitHub 用户ID
     */
    @JsonProperty("id")
    private Long id;

    /**
     * GitHub 用户节点ID
     */
    @JsonProperty("node_id")
    private String nodeId;

    /**
     * GitHub 用户头像 URL
     */
    @JsonProperty("avatar_url")
    private String avatarUrl;

    /**
     * GitHub 用户 gravatar ID
     */
    @JsonProperty("gravatar_id")
    private String gravatarId;

    /**
     * GitHub 用户详情 URL
     */
    @JsonProperty("url")
    private String url;

    /**
     * GitHub 用户主页 URL
     */
    @JsonProperty("html_url")
    private String htmlUrl;

    /**
     * GitHub 用户关注者 URL
     */
    @JsonProperty("followers_url")
    private String followersUrl;

    /**
     * GitHub 用户关注 URL
     */
    @JsonProperty("following_url")
    private String followingUrl;

    /**
     * GitHub 用户 Gists URL
     */
    @JsonProperty("gists_url")
    private String gistsUrl;

    /**
     * GitHub 用户收藏 URL
     */
    @JsonProperty("starred_url")
    private String starredUrl;

    /**
     * GitHub 用户订阅 URL
     */
    @JsonProperty("subscriptions_url")
    private String subscriptionsUrl;

    /**
     * GitHub 用户组织 URL
     */
    @JsonProperty("organizations_url")
    private String organizationsUrl;

    /**
     * GitHub 用户仓库 URL
     */
    @JsonProperty("repos_url")
    private String reposUrl;

    /**
     * GitHub 用户事件 URL
     */
    @JsonProperty("events_url")
    private String eventsUrl;

    /**
     * GitHub 用户接收事件 URL
     */
    @JsonProperty("received_events_url")
    private String receivedEventsUrl;

    /**
     * 用户类型（例如：User）
     */
    @JsonProperty("type")
    private String type;

    /**
     * 是否为站点管理员
     */
    @JsonProperty("site_admin")
    private boolean siteAdmin;

    /**
     * 用户真实姓名
     */
    @JsonProperty("name")
    private String name;

    /**
     * 用户所在公司
     */
    @JsonProperty("company")
    private String company;

    /**
     * 用户博客地址
     */
    @JsonProperty("blog")
    private String blog;

    /**
     * 用户所在地
     */
    @JsonProperty("location")
    private String location;

    /**
     * 用户电子邮箱地址
     */
    @JsonProperty("email")
    private String email;

    /**
     * 用户是否可被雇佣
     */
    @JsonProperty("hireable")
    private String hireable;

    /**
     * 用户简介
     */
    @JsonProperty("bio")
    private String bio;

    /**
     * 用户 Twitter 用户名
     */
    @JsonProperty("twitter_username")
    private String twitterUsername;

    /**
     * 用户公开仓库数量
     */
    @JsonProperty("public_repos")
    private int publicRepos;

    /**
     * 用户公开 Gists 数量
     */
    @JsonProperty("public_gists")
    private int publicGists;

    /**
     * 用户关注者数量
     */
    @JsonProperty("followers")
    private int followers;

    /**
     * 用户关注数量
     */
    @JsonProperty("following")
    private int following;

    /**
     * 用户创建时间
     */
    @JsonProperty("created_at")
    private String createdAt;

    /**
     * 用户最后更新时间
     */
    @JsonProperty("updated_at")
    private String updatedAt;


}