package com.alibaba.demo.service;

import com.alibaba.demo.provider.DemoProvider;
import com.alibaba.demo.util.DemoUtils;
import com.taobao.hsf.lightapi.ServiceFactory;

/**
 * 手动发布一个服务，用于做各类测试
 * @author Jason
 *
 */
public class PubService {

	static ServiceFactory factory = null;
	static {
		//加载SAR包，WEB方式不需要，这里仅仅是为了测试加入
		factory = DemoUtils.getServiceFactory();
	}

	public static void main(String[] args) throws InterruptedException {
		/*try {
			//加载sar
			DemoUtils.getServiceFactory();
			//代码方式发布服务
			HSFSpringProviderBean providerBean = new HSFSpringProviderBean();
			providerBean.setInterface("com.alibaba.demo.api.DemoApi");
			providerBean.setTarget(new DemoProvider());
			providerBean.setGroup("unittest");
			providerBean.init();
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		// 进行服务发布（若有发布者，无需再这里写）
		factory.provider("demoApiProvider")// 参数是一个标识，初始化后，下次只需调用provider("helloProvider")即可拿出对应服务
				.service("com.alibaba.demo.api.DemoApi")// 接口全类名
				.version("1.0.0")// 版本号
				.group("unittest")// 组别
				.impl(new DemoProvider())// 对应的服务实现
				.publish();// 发布服务，至少要调用service()和version()才可以发布服务

		System.out.println("Pub Success.");
		Thread.sleep(60*1000);
	}
}
