package com.alibaba.hsf.feature.async;

import com.taobao.hsf.exception.HSFException;
import com.taobao.hsf.tbremoting.invoke.CallbackInvocationContext;
import com.taobao.hsf.tbremoting.invoke.HSFResponseCallback;

public class DemoTestCallbackHandler implements HSFResponseCallback {

	
	public void onAppException(Throwable t) {

	}

	public void onAppResponse(Object appResponse) {
		Object msg = CallbackInvocationContext.getContext();
		System.out.println("rev msg:" + msg);
		System.out.println("onAppResponse:" + appResponse);
	}

	public void onHSFException(HSFException hsfEx) {

	}

}
