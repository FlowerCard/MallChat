package com.abin.mallchat.common.security.user;

import com.abin.mallchat.common.security.enums.ClientTypeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.management.ServiceNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/3/18 13:38
 */
@Component
public class UserDetailFactory implements InitializingBean {

    @Resource
    private List<LoginUserService> userServiceList;

    private final Map<ClientTypeEnum, LoginUserService> userServiceMap = new HashMap<>(10);

    /**
     * 根据clientId获取userService
     *
     * @param clientEnum 客户端
     * @return UserService
     */
    public LoginUserService of(ClientTypeEnum clientEnum) throws ServiceNotFoundException {
        if (!userServiceMap.containsKey(clientEnum)) {
            throw new ServiceNotFoundException("客户端不支持！".concat(clientEnum.getType()));
        }
        return userServiceMap.get(clientEnum);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (LoginUserService userService : userServiceList) {
            userServiceMap.put(userService.client(), userService);
        }
    }
}
