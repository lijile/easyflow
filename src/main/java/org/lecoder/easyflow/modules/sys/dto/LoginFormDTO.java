package org.lecoder.easyflow.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 登录表单
 *
 * @author: lijile
 * @date: 2021/10/27 9:36
 * @version: 1.0
 */
@Data
@ApiModel("登录参数")
public class LoginFormDTO {

    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不为空")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不为空")
    private String password;

}
