package com.zda.serverprovider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.xml.ws.RequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zda.serverprovider.dtos.ApiResult;
import com.zda.serverprovider.dtos.Person;
import com.zda.serverprovider.dtos.son;
@RestController
@RequestMapping("/all")
public class AllRequest {
	@Autowired
	private Environment env;
	//get
	@RequestMapping(value = "/getlist",method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List getList(@RequestParam String msg){
		List<Person> result=new ArrayList<Person>();
		for (int i = 0; i < 5; i++) {
			result.add(new Person(i,"name",env.getProperty("server.port")));
		}
		return result;
	}
	@GetMapping(value = "/getApilist",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResult<List> getApiList(@RequestParam String msg,
										@RequestParam String val){
		System.out.println(msg);
		List<Person> result=new ArrayList<Person>();
		for (int i = 0; i < 5; i++) {
			result.add(new Person(i,"name",msg));
		}
		return new ApiResult<List>(result, "success", true);
	}	
	@GetMapping(value = "/getApilist/person",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResult<List> getApiList(@RequestBody Person person){
		son son =person.getSon();
		System.out.println(son.getName()+""+son.getAge());
		List<Person> result=new ArrayList<Person>();
		for (int i = 0; i < 5; i++) {
			result.add(new Person(i,"name",person.getName()));
		}
		return new ApiResult<List>(result, "success", true);
	}
	//post
	@PostMapping(value = "/post",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Person post(@RequestBody Person p,@RequestParam String msg) {//这个string的参数名必须与路径上的同名。否则需要给requestParam加上默认值
		System.out.println("success==>"+msg);
		return p;
	}
	@PostMapping(value = "/postApiresult",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ApiResult<Person> postApiresult(@RequestBody Person p,@RequestParam String msg) {
		System.out.println("success==>"+msg);
		return new ApiResult<Person>(p, "success", true);
	}
	@PostMapping(value = "/postApiresultlist",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ApiResult<List<Person>> postApiresultlist(@RequestBody Person p,@RequestParam String msg) {
		System.out.println("success==>"+msg);
		List<Person> result=new ArrayList<Person>();
		for (int i = 0; i < 5; i++) {
			result.add(new Person(i,"name",msg));
		}
		return new ApiResult<List<Person>>(result, "success", true);
	}
	
	
	//put
	@PutMapping(value="/putPerson",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Person putPerson(@RequestBody Person p,@RequestParam String msg){
		System.out.println(p.getId() + "===" + p.getAge() + "===" + p.getName());
		System.out.println(msg);
		System.out.println();
		return p;
	}
	@PutMapping(value="/putPersonList",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Person> putPersonList(@RequestBody List<Person> list,@RequestParam String msg){
		list.forEach(p->{
			System.out.println(p.getId() + "===" + p.getAge() + "===" + p.getName());
		});
		System.out.println(msg);
		System.out.println();
		return list;
	}
	@PutMapping(value="/putApiPersonList",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ApiResult<List<Person>> putApiPersonList(@RequestBody ApiResult<List<Person>> apir,@RequestParam String msg){
	List< Person> list=apir.getResulT();
		list.forEach(p->{
			System.out.println(p.getId() + "===" + p.getAge() + "===" + p.getName());
		});
		System.out.println(msg);
		System.out.println();
		return apir;
	}
	//delete
	@DeleteMapping("/deletePersonList")
	public void deletePersonList(@RequestBody List<Person> list,@RequestParam String msg){
		System.out.println(msg);
		list.forEach(p->{
			System.out.println(p.getId() + "===" + p.getAge() + "===" + p.getName());	
		});
	}
}
