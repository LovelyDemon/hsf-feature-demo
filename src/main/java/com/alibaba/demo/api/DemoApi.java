package com.alibaba.demo.api;

import com.alibaba.demo.generic.domain.GenericTestDO;

public interface DemoApi {
	// 业务处理
	public String dealMsg(String msg);
	
	//用于测试泛化调用
	public GenericTestDO dealGenericTestDO(GenericTestDO testDO);
}
