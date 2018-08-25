package com.zda.consumer;

import java.lang.reflect.Method;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import feign.Feign;
import feign.Target;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ServletComponentScan
@EnableCircuitBreaker
@ComponentScan(basePackages={"com.zda"})
public class ConsumerApp {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ConsumerApp.class).web(true).run(args);
	}
	@Bean
    public Feign.Builder feignHystrixBuilder() {
        return HystrixFeign.builder().setterFactory(new SetterFactory() {
            @Override
            public HystrixCommand.Setter create(Target<?> target, Method method) {
                return HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(PersonFeignClient.class.getSimpleName()))// 控制 PersonFeignClient 下,所有方法的Hystrix Configuration
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(10000) // 超时配置
                        );
            }
        });
    }
}
