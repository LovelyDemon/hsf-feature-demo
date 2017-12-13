package com.alibaba.demo.util;

import com.taobao.hsf.lightapi.ServiceFactory;

public class DemoUtils {
	private static final String path="D:/dev/taobao-tomcat-7.0.59/deploy";
	
	public static ServiceFactory getServiceFactory() {
		// 这里设置Pandora地址，参数是sar包所在目录，如这里我的sar包地址是/Users/Jason/Work/AliSoft/PandoraSar/DevSar/taobao-hsf.sar，则只取前面的地址即可
		return ServiceFactory.getInstanceWithPath(path);
	}
}
