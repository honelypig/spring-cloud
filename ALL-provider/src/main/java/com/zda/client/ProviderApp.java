package com.zda.client;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.zda"})
public class ProviderApp {//这个类作为负载均衡时才用
//用来获取config-server获取svn配置
	public static void main(String[] args) {
//		new SpringApplicationBuilder(ProviderApp.class).run(args);
		//实现启用多个一样的服务
		Scanner scan = new Scanner(System.in);
		String port = scan.nextLine();
		new SpringApplicationBuilder(ProviderApp.class).properties("server.port=" + port).run(args);
		
	}
}
