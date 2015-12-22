package com.xjw.filter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xjw.Context;
import com.xjw.Context.Environment;
import com.xjw.controller.BaseController;
import com.xjw.entity.vo.Operator;
import com.xjw.utils.decode.SpecifiedCookieToMap;

public class PermissionFilter extends HandlerInterceptorAdapter {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Value("${LOGIN.SESSION.ID}")
	public String SESSION_ID_FLAG = "LoginUserInfo";
	
	@Value("${LOGIN.URL}")
	public String LOGIN_URL;
	
	public static final int SESSION_TIMEOUT = 60*60;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception{
		
		if(Context.current.environment.equals(Environment.DEVELOP)){
			Operator operator = new Operator();
			operator.setUserId("123");
			operator.setUserCode("3987654");
			operator.setDistributionCode("rfd");
			operator.setExpressId("7");
			operator.setPwd("123456");
			operator.setUserName("小小李");
			operator.setDistributionName("北京测试站");
			if(handler instanceof HandlerMethod && ((HandlerMethod) handler).getBean() instanceof BaseController){
				((BaseController)((HandlerMethod) handler).getBean()).setOperator(operator);
			}
			return true;
		}
		String sessionId = getSessionId(request, response);
		if(StringUtils.isNotBlank(sessionId)){
			Map<String, Object> map = SpecifiedCookieToMap.getLocalCookie(request, SESSION_ID_FLAG);
			if(handler instanceof HandlerMethod && ((HandlerMethod) handler).getBean() instanceof BaseController){
				Operator operator = convertMapToOperator(map);
				if(StringUtils.isBlank(operator.getUserCode())){
					response.sendRedirect(LOGIN_URL);
					return false;
				}
				((BaseController)((HandlerMethod) handler).getBean()).setOperator(operator);
			}
			request.getSession().setAttribute("currentUserName", 
					map != null ? (map.get("UserName") == null ? "无记录" : map.get("UserName").toString()) : "无记录");
			return true;
		}
		response.sendRedirect(LOGIN_URL);
		return false;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception{
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
	private String getSessionId(HttpServletRequest request, HttpServletResponse response){
		String sessionId = null;
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(SESSION_ID_FLAG)){
					sessionId = cookie.getValue();
					setCookie(response, sessionId);
				}
			}
		}
		return sessionId;
	}
	
	private void setCookie(HttpServletResponse response, String sessionId){
		Cookie cookie = new Cookie(SESSION_ID_FLAG, sessionId);
		cookie.setPath("/");
		cookie.setMaxAge(SESSION_TIMEOUT);
		response.addCookie(cookie);
	}
	
	private Operator convertMapToOperator(Map<String, Object> map) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		if (map != null) {
			log.debug(map);
			Operator operator = new Operator();
			Field[] fields = Operator.class.getDeclaredFields();

			for (Field field : fields) {
				String field_name = field.getName();
				String key = null;
				if (field_name.equals("pwd")) {
					key = "pwd";
				} else {
					key = field_name.substring(0, 1).toUpperCase() + field_name.substring(1);
				}
				String field_set_method_name = "set" + field_name.substring(0, 1).toUpperCase()
						+ field_name.substring(1);
				log.info(map.get(key) == null ? "未解析到该属性" : map.get(key));
				Operator.class.getMethod(field_set_method_name, String.class).invoke(operator,
						map.get(key) == null ? "未解析到该属性" : map.get(key).toString());

			}

			return operator;
		}

		log.error("未从cookie中解密出当前操作人信息...");

		return null;
	}
}
