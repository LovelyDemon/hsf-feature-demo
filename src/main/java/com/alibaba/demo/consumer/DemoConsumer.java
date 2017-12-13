package com.alibaba.demo.consumer;

import com.alibaba.demo.api.DemoApi;
import com.taobao.hsf.tbremoting.invoke.CallbackInvocationContext;

import java.text.SimpleDateFormat;

/**
 * 消费者，用于测试
 * @author Jason
 *
 */
public class DemoConsumer {

	private DemoApi demoApi;

	public void setDemoApi(DemoApi demoApi) {
		this.demoApi = demoApi;
	}

	public DemoApi getDemoApi() {
		return demoApi;
	}

	/**
	 * 消费者消费服务
	 * @throws InterruptedException
	 */
	public void requestService() throws InterruptedException {
		//等待地址接收，停顿3s
		Thread.sleep(3000);
		//通过上下文传递数据
		CallbackInvocationContext.setContext("hi,aync callback.");
		//调用服务
		String msg=demoApi.dealMsg("hsf-test-" + new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(System.currentTimeMillis()));
		//得到的调用结果
		System.out.println("Tst HSF Consumer:" + msg);
	}

}
