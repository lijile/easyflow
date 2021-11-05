package org.lecoder.easyflow.modules.sys.entity;

import org.lecoder.easyflow.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lijile
 * @since 2021-10-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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
