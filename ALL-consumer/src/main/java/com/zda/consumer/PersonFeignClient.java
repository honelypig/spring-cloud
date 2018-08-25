package com.zda.consumer;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zda.consumer.dtos.ApiResult;
import com.zda.consumer.dtos.Person;

@FeignClient(name="all-client",fallback=PersonFeignClientFallBack.class)
public interface PersonFeignClient {
	@RequestMapping(value = "/all/getlist", method = RequestMethod.GET)
	public List<Person> getPerson(@RequestParam("msg") String msg);
	
	@RequestMapping(value = "/all/getApilist/person", method = RequestMethod.GET,consumes="application/json")
	public ApiResult<List<Person>> getPerson( Person person);
	
	@RequestMapping(value="/all/person",method=RequestMethod.GET)
	public String person();
}
