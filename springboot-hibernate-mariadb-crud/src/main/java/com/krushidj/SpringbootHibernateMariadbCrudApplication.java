package com.krushidj;

import com.krushidj.utils.MethodUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// @ComponentScan(basePackages = "com.krushidj.modules.admin.controller")
public class SpringbootHibernateMariadbCrudApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootHibernateMariadbCrudApplication.class, args);
	}
//	@Bean
//	public MethodUtil methodUtil(){
//		return  new MethodUtil();
//	}
}

