package com.xjw;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Context {
	
	public static final String SYSTEM_ENV_FLAG = "PX-M1_ENV";
	
	public Environment environment = Environment.DEVELOP;

	public static Context current;
	
	@Value("${spring.PX-M1_ENV}")
	public String active;
	
	public enum Environment {
		DEVELOP("DEVELOP"), TEST("TEST"), PRODUCTION("PRODUCTION"), UNIT_TEST("UNIT_TEST");
		
		public String name;

		private Environment(String name) {
			this.name = name;
		}
	}
	
	@PostConstruct
	public void init() {
		Context.current = this;
		initEnvironment(this);

		if ((this.environment == Environment.TEST || this.environment == Environment.DEVELOP)) {
			
		}
	}

	public void initEnvironment(Context context) {
		
		if(StringUtils.isNotBlank(active)){
			context.environment = Environment.valueOf(active);
		}
		
		if (context.environment != Environment.UNIT_TEST
				&& System.getenv().containsKey(SYSTEM_ENV_FLAG)) {
			if (Environment.DEVELOP.name.equals(System.getenv().get(
					SYSTEM_ENV_FLAG))) {
				context.environment = Environment.DEVELOP;
			} else if (Environment.TEST.name.equals(System.getenv().get(
					SYSTEM_ENV_FLAG))) {
				context.environment = Environment.TEST;
			} else if (Environment.PRODUCTION.name.equals(System.getenv().get(
					SYSTEM_ENV_FLAG))) {
				context.environment = Environment.PRODUCTION;
			}
		}
	}
}
