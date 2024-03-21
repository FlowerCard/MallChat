package com.abin.mallchat.common.user.domain.vo.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Author HuaPai
 * @Email flowercard591@gmail.com
 * @Date 2024/3/21 18:54
 * @Description 用户注册请求
 */
@ApiModel(value = "用户注册请求")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterReq {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

}
