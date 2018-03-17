package com.user.stjia;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class EmailNotifier implements ApplicationListener<ApplicationEvent> {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		if (event instanceof EmailEvent) {
			EmailEvent emailEvent = (EmailEvent) event;
			System.out.println(emailEvent.getAddress() + emailEvent.getText());
			
			System.out.println();
		} else {
			System.out.println("其他事情：" + event);
		}
	}

}
