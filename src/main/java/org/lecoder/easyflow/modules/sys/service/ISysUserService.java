package org.lecoder.easyflow.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.lecoder.easyflow.modules.sys.dto.LoginFormDTO;
import org.lecoder.easyflow.modules.sys.dto.UserDTO;
import org.lecoder.easyflow.modules.sys.entity.SysUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lijile
 * @since 2021-10-26
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 登录
     * @author: lijile
     * @date: 2021/10/27 9:55
     * @param loginFormDTO
     * @return
     */
    UserDTO login(LoginFormDTO loginFormDTO);

    /**
     * 根据token获取用户信息
     * @author: lijile
     * @date: 2021/10/27 10:29
     * @param token
     * @return
     */
    UserDTO getUserByToken(String token);
}
