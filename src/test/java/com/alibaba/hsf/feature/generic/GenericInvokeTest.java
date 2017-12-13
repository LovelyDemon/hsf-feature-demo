package com.alibaba.hsf.feature.generic;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.demo.generic.domain.GenericTestDO;
import com.alibaba.demo.util.DemoUtils;
import com.alibaba.dubbo.common.utils.PojoUtils;
import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;

import com.taobao.hsf.app.spring.util.HSFSpringConsumerBean;
/**
 * 泛化调用
 * 
 * <pre>
 * 测试步骤
 * 1.先启动一个服务
 * 2.进行泛化调用
 * 建议:
 *调用方在不确定格式的情况下，可以写个单元测试，测试时依赖需要泛化调用的二方包，使用HSF提供的工具类 com.taobao.hsf.util.PojoUtils 的 generalize() 方法来生成一个 POJO Bean 的字符串描述格式。
 * </pre>
 * 
 * @author Jason
 * 
 * 
 *
 */
public class GenericInvokeTest {

	static {
		//加载SAR包，WEB方式不需要，这里仅仅是为了测试加入
		DemoUtils.getServiceFactory();
	}

	/**
	 * 
	 * 注意：测试之前需要保证服务是发布成功的，版本、分组都是正确的，否则这里调用会失败
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testGenericInvoke() throws Exception {

		// xml方式加载服务消费者
		ClassPathXmlApplicationContext consumerContext = new ClassPathXmlApplicationContext(
				"hsf-generic-consumer-beans.xml");

		/*HSFSpringConsumerBean consumerBean = new HSFSpringConsumerBean();
		consumerBean.setInterfaceName("com.alibaba.demo.api.DemoApi");
		consumerBean.setGeneric("true"); // 设置 generic 为 true
		consumerBean.setGroup("unittest");
		consumerBean.setVersion("1.0.0");
		consumerBean.init();
		// 强转接口接口为 GenericService
		GenericService svc = (GenericService) consumerBean.getObject();*/

		try {
			// *****************************测试一，简单对象测试，如String
			// 获取泛化对象
			GenericService svc = (GenericService) consumerContext.getBean("demoApi");
			// 休息3s调用
			Thread.sleep(3 * 1000);
			// 服务泛化调用
			System.out.println(
					"invoke:" + svc.$invoke("dealMsg", new String[] { "java.lang.String" }, new Object[] { "hello" }));
			// 其他类型的传入，如java.util.Map、long、Integer.class.getName()

			// *****************************测试二，对象测试，如DO
			// 第一步构造实体对象
			GenericTestDO genericTestDO = new GenericTestDO();
			genericTestDO.setId(1980L);
			genericTestDO.setName("genericTestDO-tst");
			// 使用 PojoUtils 生成二方包pojo的描述
			Object comp = PojoUtils.generalize(genericTestDO);
			// 服务泛化调用
			System.out.println("DO invoke:" + svc.$invoke("dealGenericTestDO",
					new String[] { "com.alibaba.demo.generic.domain.GenericTestDO" }, new Object[] { comp }));

		} catch (BeansException e) {
			e.printStackTrace();
		} catch (GenericException e) {
			e.printStackTrace();
		}
	}
}
