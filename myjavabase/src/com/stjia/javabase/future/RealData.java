package com.stjia.javabase.future;

import java.util.concurrent.Callable;

import com.stjia.javabase.bean.DataBean;

public class RealData implements Callable<DataBean> {
	
	private String para;
	
	public RealData(String para) {
		// TODO Auto-generated constructor stub
		this.para = para;
	}

	@Override
	public DataBean call() throws Exception {
		// TODO Auto-generated method stub 
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 100; i++) {
			sb.append(para + i + ";");
			Thread.sleep(100);
		}
		DataBean dataBean = new DataBean();
		dataBean.setStr(sb.toString());
		return dataBean;
	}

	

	
}
