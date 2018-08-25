package com.zda.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.zda"})
public class ConfigClientApp {
//用来获取config-server获取svn配置
	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigClientApp.class).run(args);
	}
}
