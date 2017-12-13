package com.alibaba.hsf.feature.async;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.demo.consumer.DemoConsumer;
import com.alibaba.demo.util.DemoUtils;
import com.taobao.hsf.exception.HSFException;
import com.taobao.hsf.tbremoting.invoke.HSFFuture;
import com.taobao.hsf.tbremoting.invoke.HSFResponseFuture;

/**
 * 异步调用
 * 
 * <pre>
 * 测试先先发布一个服务，测试服务写在PubService类中，手动启动即可
 * 1.future模式
 * 2.callback模式
 * </pre>
 * 
 * @author Jason
 *
 */
public class AsyncTest {
	static {
		// 初始化SAR包，WEB方式不需要，这里仅仅是为了测试加入
		DemoUtils.getServiceFactory();
	}

	/**
	 * Future模式，可以多个业务并发处理，在最后进行获取处理结果情况
	 * 
	 * @throws Throwable
	 * @throws InterruptedException
	 * @throws HSFException
	 */
	@Test
	public void testOneAsyncInvokeFuture() throws HSFException, InterruptedException, Throwable {
		// xml方式加载服务消费者
		ClassPathXmlApplicationContext consumerContext = new ClassPathXmlApplicationContext(
				"hsf-async-future-consumer-beans.xml");
		// 获取bean
		DemoConsumer demoConsumer = (DemoConsumer) consumerContext.getBean("demoConsumer");

		Thread.sleep(3000);
		// 开始处理业务1
		demoConsumer.requestService();
		HSFFuture future = HSFResponseFuture.getFuture();
		// 这里处理一些业务
		// ......

		// 如果是一个，直接可以获取结果
		String msg = (String) future.getResponse(3000);
		System.out.println("one future msg:" + msg);
	}

	@Test
	public void testMultAsyncInvokeFuture() throws Throwable {
		// 定义存放future对象的集合
		List<HSFFuture> futures = new ArrayList<HSFFuture>();
		// xml方式加载服务消费者
		ClassPathXmlApplicationContext consumerContext = new ClassPathXmlApplicationContext(
				"hsf-async-future-consumer-beans.xml");
		// 获取bean
		DemoConsumer demoConsumer = (DemoConsumer) consumerContext.getBean("demoConsumer");

		Thread.sleep(3000);
		// 开始处理业务1
		demoConsumer.requestService();
		HSFFuture future1 = HSFResponseFuture.getFuture();
		futures.add(future1);

		// 开始处理业务2
		demoConsumer.requestService();
		HSFFuture future2 = HSFResponseFuture.getFuture();
		futures.add(future2);

		// 开始处理业务3
		demoConsumer.requestService();
		HSFFuture future3 = HSFResponseFuture.getFuture();
		futures.add(future3);

		// 所有业务异步处理发出，这里获取所有的异步处理结果，进行其他处理
		for (HSFFuture hsfFuture : futures) {
			String msg = (String) hsfFuture.getResponse(3000);
			System.out.println("mult future msg:" + msg);
		}
		System.out.println("test comp.");
	}

	// *****************************************************callback**********************************************************

	/**
	 * callback模式，由于通过lightapi测试会有类冲突的情况，该测试采用servlet测试，详见
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testAsyncInvokeCallback() throws InterruptedException {
		
	}

}
