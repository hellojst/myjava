package com.user.stjia;

import java.util.Date;
import java.util.Locale;

import javax.naming.Context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

public class TestBean {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Person person = context.getBean("chinese", Person.class);
		person.useAxe();
		Person person2 = context.getBean("yellowPerson", Person.class);
		person2.useAxe();
		person2.useAxe("斩月");
		ApplicationContext messctx = new ClassPathXmlApplicationContext("message.xml");
		String time = messctx.getMessage("time", new Object[] {new Date(),"sss"}, Locale.getDefault(Locale.Category.FORMAT));
		System.out.println(time);
		
		Person person3 = messctx.getBean("yellowPersonWithMessage", Person.class);
		person3.sayTime();
		EmailEvent event = new EmailEvent("test", "10.170.18.21", "email:");
		context.publishEvent(event);
		NIOtest niOtest = new NIOtest();
		niOtest.makeFile();
				
	}
}
