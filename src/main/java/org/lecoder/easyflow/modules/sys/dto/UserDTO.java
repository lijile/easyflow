package org.lecoder.easyflow.modules.sys.dto;

import lombok.Data;

/**
 * 用户传输对象
 *
 * @author: lijile
 * @date: 2021/10/27 10:43
 * @version: 1.0
 */
@Data
public class UserDTO {
    private Integer id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String fullname;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
}
