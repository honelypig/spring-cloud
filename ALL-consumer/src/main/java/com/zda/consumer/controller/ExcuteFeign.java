package com.zda.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import com.zda.consumer.PersonFeignClient;
import com.zda.consumer.dtos.ApiResult;
import com.zda.consumer.dtos.Person;
import com.zda.consumer.dtos.son;

@RestController
public class ExcuteFeign {
	
	@Autowired
	private PersonFeignClient personFeignClient;
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@GetMapping("/ServiceInstacne")
	public String getserviceInstance(){
		ServiceInstance serviceInstance=loadBalancerClient.choose("all-client");
		return serviceInstance.getServiceId()+"==>"+serviceInstance.getHost()+"==>"+serviceInstance.getPort();
	}
	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public List<Person> getList(@RequestParam String msg) {
		List<Person> list = personFeignClient.getPerson(msg);
		return list;
	}
	@GetMapping(value="/sendPerson")
	public String sendPerson() {
	Person person=new Person();
	son son=new son();
	son.setName("张三");
	son.setAge(21);
	person.setAge("44");
	person.setSon(son);
	person.setId(11);
	person.setName("张三他爸");
	ApiResult<List<Person>> list=personFeignClient.getPerson(person);
		list.getResulT().forEach(System.out::println);
		return "success";
	}
	@GetMapping(value="/TOhystrix")
	public String person() throws Exception {
		System.out.println("coming");
//		HystrixCircuitBreaker breaker = HystrixCircuitBreaker.Factory
//				.getInstance(HystrixCommandKey.Factory
//						.asKey("PersonFeignClient#person()"));
//		System.out.println("断路器状态：" + breaker.isOpen());
		return personFeignClient.person();
	}
}
