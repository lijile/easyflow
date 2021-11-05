package org.lecoder.easyflow.common.toolkit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 网络工具
 *
 * @author: lijile
 * @date: 2021/11/4 16:01
 * @version: 1.0
 */
public class NetworkUtils {

    public static String getSessionToken(HttpServletRequest request) {
        // 1.从cookies获取
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("session_token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        // 2.从header获取
        return request.getHeader("Authorization");
    }

}
