package com.xjw.utils.decode;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class SpecifiedCookieToMap {
	/**
	 * 获取本地的cookie
	 * 
	 * @param request
	 * @param cookieName
	 *            名称
	 * @return
	 * @throws Exception 
	 */
	public static Map<String, Object> getLocalCookie(
			HttpServletRequest request, String cookieName) throws Exception {
		// 根据名字获取本地的用户登录的cookie
		Cookie cookie = CookieUtils.getCookie(request, cookieName);
		String cookieValue = null;
		if (cookie != null) {
			cookieValue = cookie.getValue();
		}

		String decryptCookieValue = null;
		// 写死做测试
		//cookieValue = CookiesEncrypt.huxinyuanmsg;

		// 判断cookie是否存在，对cookie进行解密

		if (StringUtils.isNotBlank(cookieValue)) {// 如果cookie的值不为空
			// 对加密后的cookie进行解密
			decryptCookieValue = CookiesEncrypt.decrypt(cookieValue);
		}

		// 把json解析成map
		Map<String, Object> cookieJson = CookiesEncrypt
				.getCookieJson(decryptCookieValue);

		return cookieJson;
	}
}
