package com.alibaba.demo.provider;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Set;

import com.alibaba.demo.api.DemoApi;
import com.alibaba.demo.generic.domain.GenericTestDO;
import com.alibaba.dubbo.rpc.RpcContext;

public class DemoProvider implements DemoApi {

	public String dealMsg(String msg) {
		System.out.println("Provider:" + new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(System.currentTimeMillis()) + "，Recive MSG:" + msg);
		try {
			implicit();
			
			// 模拟处理一些业务
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 返回一些结果
		return msg + "," + new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(System.currentTimeMillis());

	}

	/**
	 * 隐式传参测试
	 */
	private void implicit() {

		RpcContext rpcContext = RpcContext.getContext();
		// 接收隐式传参
		if (rpcContext != null) {
			// 单个接收处理
			String keyVal = rpcContext.getAttachment("key");
			if (keyVal != null){
				System.out.println("RpcContext rev value:" + keyVal);
				//处理完毕进行清理
				rpcContext.clearAttachments();
			}
			
			//************************************************************************************************************
			System.out.println("****************************************************************************");
			// 多个接收处理
			Map<String, String> map = rpcContext.getAttachments();
			if (map != null && !map.isEmpty()) {
				Set<String> set = map.keySet();
				for (String key : set) {
					System.out.println("RpcContext rev map value:" + map.get(key));
				}
				//处理完毕进行清理
				rpcContext.clearAttachments();
			}
		}

	}

	public GenericTestDO dealGenericTestDO(GenericTestDO testDO) {
		if (testDO != null) {
			System.out.println("input DO:" + testDO.toString());
		}
		return testDO;
	}

}
