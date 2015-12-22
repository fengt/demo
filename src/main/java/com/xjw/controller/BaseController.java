package com.xjw.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.ModelAndView;

import com.xjw.Context;
import com.xjw.entity.vo.Operator;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseController {

	protected Log log = LogFactory.getLog(this.getClass());
	
	private Operator operator;

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	protected void debug(Object message){
		if(log.isDebugEnabled())
			log.debug(message);
	}
	
	protected void debug(Object message, Throwable t){
		if(log.isDebugEnabled())
			log.debug(message, t);
	}
	
	protected void info(Object message){
		log.info(message);
	}
	
	protected void error(Object message, Throwable t){
		log.error(message);
	}
	
	protected ModelAndView createModel(){
		return createModel(null);
	}
	
	protected ModelAndView createModel(String viewName){
		ModelAndView model = new ModelAndView();
		model.setViewName(viewName);
		
		//TODO 根据环境设置layout
		if(Context.current.environment.equals(Context.Environment.DEVELOP)){
			model.addObject("layout", "layout");
		}
		
		return model;
	}
}
