package com.alibaba.demo.service;

import com.alibaba.demo.api.DemoApi;
import com.alibaba.demo.util.DemoUtils;
import com.taobao.hsf.app.spring.util.HSFSpringConsumerBean;

/**
 * 手动订阅一个服务，用于做各类测试
 * @author Jason
 *
 */
public class SubService {

	public static void main(String[] args) throws Exception {
		HSFSpringConsumerBean consumerBean=null;
		try {
			//加载sar
			DemoUtils.getServiceFactory();
			//代码方式发布服务
			consumerBean=new HSFSpringConsumerBean();
			consumerBean.setInterface("com.alibaba.demo.api.DemoApi");
			consumerBean.setVersion("1.0.0");
			consumerBean.setGroup("unittest");
			consumerBean.syncInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DemoApi demoApi=(DemoApi) consumerBean.getObject();
		String msg=demoApi.dealMsg("sub success.");
		System.out.println("Sub Success."+msg);
//		Thread.sleep(60*60*1000);
	}

}
