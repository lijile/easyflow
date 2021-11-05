package org.lecoder.easyflow.modules.sys.interceptor;

import cn.hutool.json.JSONUtil;
import org.lecoder.easyflow.common.toolkit.NetworkUtils;
import org.lecoder.easyflow.common.toolkit.RequestHolder;
import org.lecoder.easyflow.common.toolkit.SpringContextHolder;
import org.lecoder.easyflow.common.web.CommonResult;
import org.lecoder.easyflow.modules.sys.dto.UserDTO;
import org.lecoder.easyflow.modules.sys.service.ISysUserService;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录拦截器，负责给请求线程设置用户信息
 *
 * @author: lijile
 * @date: 2021/10/27 10:41
 * @version: 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDTO currentUser = getUser(request);
        if (currentUser == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            CommonResult commonResult = CommonResult.notlogin();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "application/json");
            response.getWriter().print(JSONUtil.toJsonStr(commonResult));
            return false;
        }
        RequestHolder.setCurrentUser(currentUser);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestHolder.removeCurrentUser();
    }

    private UserDTO getUser(HttpServletRequest request) {
        String token = NetworkUtils.getSessionToken(request);
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        ISysUserService userService = SpringContextHolder.getBean(ISysUserService.class);
        return userService.getUserByToken(token);
    }

}
