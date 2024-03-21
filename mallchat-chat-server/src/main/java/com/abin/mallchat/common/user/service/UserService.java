package com.abin.mallchat.common.user.service;

import com.abin.mallchat.common.user.domain.dto.ItemInfoDTO;
import com.abin.mallchat.common.user.domain.dto.SummeryInfoDTO;
import com.abin.mallchat.common.user.domain.entity.User;
import com.abin.mallchat.common.user.domain.vo.request.user.*;
import com.abin.mallchat.common.user.domain.vo.response.user.BadgeResp;
import com.abin.mallchat.common.user.domain.vo.response.user.UserInfoResp;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author <a href="https://github.com/zongzibinbin">abin</a>
 * @since 2023-03-19
 */
public interface UserService {

    /**
     * 获取前端展示信息
     *
     * @param uid
     * @return
     */
    UserInfoResp getUserInfo(Long uid);

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    User getUserInfo(String username);

    /**
     * 修改用户名
     *
     * @param uid
     * @param req
     */
    void modifyName(Long uid, ModifyNameReq req);

    /**
     * 用户徽章列表
     *
     * @param uid
     */
    List<BadgeResp> badges(Long uid);

    /**
     * 佩戴徽章
     *
     * @param uid
     * @param req
     */
    void wearingBadge(Long uid, WearingBadgeReq req);

    /**
     * 用户注册，需要获得id
     *
     * @param user
     */
    void register(User user);

    /**
     * 用户名注册<br>
     * 手动注册
     *
     * @param user 用户注册信息
     * @return 是否成功
     */
    Boolean register(UserRegisterReq user);

    void black(BlackReq req);

    /**
     * 获取用户汇总信息
     *
     * @param req
     * @return
     */
    List<SummeryInfoDTO> getSummeryUserInfo(SummeryInfoReq req);

    List<ItemInfoDTO> getItemInfo(ItemInfoReq req);

    /**
     * 根据 GitHub ID 获取用户信息
     *
     * @param gitHubId GitHub ID
     * @return 用户信息
     */
    User getUserInfoByGitHubId(Long gitHubId);
}
