package com.alibaba.hsf.feature.unit.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.demo.api.DemoApi;
import com.alibaba.demo.consumer.DemoConsumer;
import com.alibaba.demo.provider.DemoProvider;
import com.alibaba.demo.util.DemoUtils;
import com.taobao.hsf.lightapi.ConsumerService;
import com.taobao.hsf.lightapi.ServiceFactory;

/**
 * 通过LightApi方式进行单元化测试（提供Bean方式和XML方式进行单元测试）
 * 
 * @author Jason
 *
 */
public class LightApiTest {

	static ServiceFactory factory = null;
	static {
		//加载SAR包，WEB方式不需要，这里仅仅是为了测试加入
		factory = DemoUtils.getServiceFactory();
	}

	/**
	 * 1.通过设置Bean方式进行服务发布
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBeanPubOrSubService() throws Exception {
		// 进行服务发布（若有发布者，无需再这里写）
		factory.provider("demoApiProvider")// 参数是一个标识，初始化后，下次只需调用provider("helloProvider")即可拿出对应服务
				.service("com.alibaba.demo.api.DemoApi")// 接口全类名
				.version("1.0.0")// 版本号
				.group("unittest")// 组别
				.impl(new DemoProvider())// 对应的服务实现
				.publish();// 发布服务，至少要调用service()和version()才可以发布服务

		// 进行服务消费
		factory.consumer("demoApiConsumer")// 参数是一个标识，初始化后，下次只需调用consumer("helloConsumer")即可直接拿出对应服务
				.service("com.alibaba.demo.api.DemoApi")// 接口全类名
				.version("1.0.0")// 版本号
				.group("unittest")// 组别
				.subscribe();

		ConsumerService consumerService = factory.consumer("demoApiConsumer");
		// 同步等待地址推送，最多6秒。
		consumerService.sync();
		//subscribe()方法返回对应的接口
		DemoApi log4jService = (DemoApi) consumerService.subscribe();
		// 调用服务方法
		System.out.println("bean -> msg rec success:-" + log4jService.dealMsg("deal data."));
	}

	/**
	 * 2.测试通过XML方式进行加载提供者和消费者进行单元测试
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testXMLPubOrSubService() throws InterruptedException {
		// xml方式加载服务提供者
		new ClassPathXmlApplicationContext("hsf-provider-beans.xml");
		// xml方式加载服务消费者
		ClassPathXmlApplicationContext consumerContext = new ClassPathXmlApplicationContext("hsf-consumer-beans.xml");
		// 获取bean
		DemoConsumer demoConsumer = (DemoConsumer) consumerContext.getBean("demoConsumer");
		// 服务调用
		demoConsumer.requestService();
	}

}
