package com.zda.consumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.zda.consumer.dtos.ApiResult;
import com.zda.consumer.dtos.Person;

@Component
public class PersonFeignClientFallBack implements PersonFeignClient {

	@Override
	public List<Person> getPerson(String msg) {
		// TODO Auto-generated method stub
		List<Person> list=new ArrayList<Person>() ;
		list.add(new Person(1, "走fallback", ""));
		return list;
	}

	@Override
	public ApiResult<List<Person>> getPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String person() {
		// TODO Auto-generated method stub
		return "fallBack value";
	}

}
