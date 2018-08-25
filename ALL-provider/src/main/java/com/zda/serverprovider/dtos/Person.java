package com.zda.serverprovider.dtos;

public class Person {
	public Person() {
		super();
	}

	public Person(Integer id, String name, String age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	private Integer id;
	private String name;
	private String age;
private son son;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public son getSon() {
		return son;
	}

	public void setSon(son son) {
		this.son = son;
	}

}
