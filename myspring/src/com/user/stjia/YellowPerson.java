package com.user.stjia;

import java.util.Date;
import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class YellowPerson implements Person,ApplicationContextAware{

	private Axe axe;
	private ApplicationContext context;
	
	
	public YellowPerson(Axe axe) {
		this.axe = axe;
	}
	

	public YellowPerson() {
		super();
	}




	@Override
	public void useAxe() {
		// TODO Auto-generated method stub
		System.out.println("黄种人用" + axe.chop());
	}

	public String useAxe(String use) {
		// TODO Auto-generated method stub
		System.out.println("黄种人用" + axe.chop());
		return use;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		this.context = arg0;
	}
	
	public void sayTime() {
		String time = context.getMessage("time", new Object[] {new Date(),"sss"}, Locale.getDefault(Locale.Category.FORMAT));
		System.out.println("时间是：" + time);
	};
	

}
