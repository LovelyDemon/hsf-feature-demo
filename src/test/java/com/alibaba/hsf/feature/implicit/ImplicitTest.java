package com.alibaba.hsf.feature.implicit;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.demo.consumer.DemoConsumer;
import com.alibaba.demo.util.DemoUtils;
import com.alibaba.dubbo.rpc.RpcContext;

/**
 * 用于隐式传参测试demo
 * 
 * @author Jason
 *
 */
public class ImplicitTest {

	static {
		//加载SAR包，WEB方式不需要，这里仅仅是为了测试加入
		DemoUtils.getServiceFactory();
	}

	@Test
	public void testImplicitArgs() throws InterruptedException {
		// xml方式加载服务消费者
		ClassPathXmlApplicationContext consumerContext = new ClassPathXmlApplicationContext("hsf-consumer-beans.xml");
		// 获取bean
		DemoConsumer demoConsumer = (DemoConsumer) consumerContext.getBean("demoConsumer");

		Thread.sleep(3000);

		// 设置隐式传参
		RpcContext.getContext().setAttachment("key", "RpcContext args test");
		// 开始处理业务1
		demoConsumer.requestService();

		// ****************************************多个参数处理

		Map<String, String> map = new HashMap<String, String>();
		map.put("param1", "param1 test");
		map.put("param2", "param2 test");
		map.put("param3", "param3 test");
		map.put("param4", "param4 test");
		map.put("param5", "param5 test");
		RpcContext rpcContext = RpcContext.getContext();
		rpcContext.setAttachments(map);

		// 开始处理业务2
		demoConsumer.requestService();

		System.out.println(this.getClass().getName() + ",OK.");
	}
}
