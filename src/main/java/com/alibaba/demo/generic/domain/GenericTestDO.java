package com.alibaba.demo.generic.domain;

/**
 * 测试对象，用于泛化测试
 * @author Jason
 *
 */
public class GenericTestDO {

	private long id;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "GenericTestDO [id=" + id + ", name=" + name + "]";
	}

	
}
