package com.user.stjia;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 切面类
 * @author stjia
 * @time 2018年3月17日
 */
@Aspect
public class LogAspect {
	
	/**
	 * 所有方法的切入点
	 * @param rvt
	 */
	@AfterReturning(returning="rvt",pointcut="execution(* com.user.stjia.*.*(..))")
	public void log(Object rvt) {
		System.out.println("获取目标返回值：" + rvt);
		System.out.println("模拟日志记录功能");
	}
	
	@Around("execution(* com.user.stjia.*.*(..))")
	public Object processTx(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("执行目标前，模拟开始事务。。。");
		//获取入参
		Object[] args = jp.getArgs();
		if (args != null && args.length >= 1) {
			if (args[0] instanceof String) {
				args[0] = "[增加的前缀]" + args[0];
			}
		}
		
		//已改变后的参数去执行目标方法，并保存方法执行后的返回值
		Object rvt = jp.proceed(args);
		if (rvt != null && rvt instanceof String) {
			rvt = "改变后的" + rvt;
		} 
		return rvt;
	}
}
