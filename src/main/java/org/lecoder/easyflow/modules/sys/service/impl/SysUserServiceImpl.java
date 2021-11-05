package org.lecoder.easyflow.modules.sys.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lecoder.easyflow.common.exception.FlowException;
import org.lecoder.easyflow.modules.sys.constant.CacheConsts;
import org.lecoder.easyflow.modules.sys.dto.LoginFormDTO;
import org.lecoder.easyflow.modules.sys.dto.UserDTO;
import org.lecoder.easyflow.modules.sys.entity.SysUser;
import org.lecoder.easyflow.modules.sys.mapper.SysUserMapper;
import org.lecoder.easyflow.modules.sys.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lijile
 * @since 2021-10-26
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public UserDTO login(LoginFormDTO loginFormDTO) {
        SysUser user = userMapper.selectOne(new QueryWrapper<SysUser>().eq("username", loginFormDTO.getUsername()));
        if (user == null || !user.getPassword().equals(SecureUtil.md5(loginFormDTO.getPassword()))) {
            throw new FlowException("用户名或密码错误！");
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public UserDTO getUserByToken(String token) {
        Cache cache = cacheManager.getCache(CacheConsts.USER_TOKEN);
        return cache.get(token, UserDTO.class);
    }

    @Autowired private SysUserMapper userMapper;

    @Autowired private CacheManager cacheManager;
}
