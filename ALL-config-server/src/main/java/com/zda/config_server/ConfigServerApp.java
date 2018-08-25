package com.zda.config_server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigServerApp {
//作为从svn获取配置文件的启动类
	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigServerApp.class).run(args);
	}
}
