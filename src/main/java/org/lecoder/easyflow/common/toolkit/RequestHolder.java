package org.lecoder.easyflow.common.toolkit;

import org.lecoder.easyflow.modules.sys.dto.UserDTO;

/**
 * 前线程请求
 * @author: lijile
 * @date: 2021/10/27 10:44
 * @version: 1.0
 */
public class RequestHolder {
    /**
     * 获取当前用户
     * @author: lijile
     * @date: 2021/10/27 10:45
     * @return
     */
    public static UserDTO getCurrentUser() {
        return userThreadLocal.get();
    }

    /**
     * 设置当前用户
     * @author: lijile
     * @date: 2021/10/27 10:45
     * @return
     */
    public static void setCurrentUser(UserDTO user) {
        userThreadLocal.set(user);
    }

    /**
     * 移除当前线程的用户信息
     * @author: lijile
     * @date: 2021/10/27 10:45
     * @return
     */
    public static void removeCurrentUser() {
        userThreadLocal.remove();
    }

    private static final ThreadLocal<UserDTO> userThreadLocal = new ThreadLocal<>();
}
