package com.aspectj.annotation;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切面类
 * @author kevin
 *
 */
@Aspect
@Component
public class MyAspect {
	/*
	 * 定义切入点
	 */
	@Pointcut("execution(* com.dynamic.jdk.*.*(..))")
	private void myPointCut() {
		
	}
	/*
	 * 前置通知
	 */
	@Before("myPointCut()")
	public void before(JoinPoint jp) {
		System.out.print("前置通知：模拟权限控制");
		System.out.println("目标类对象"+jp.getTarget()+"，被增强处理的方法："+jp.getSignature().getName());
	}
	/*
	 * 后置返回通知
	 */
	@AfterReturning("myPointCut()")
	public void afterReturning(JoinPoint jp) {
		System.out.print("后置返回通知：模拟删除临时文件");
		System.out.println("被增强处理的方法："+jp.getSignature().getName());
	}
	/*
	 * 环绕通知
	 */
	@Around("myPointCut()")
	public Object around(ProceedingJoinPoint pjp)throws Throwable {
		System.out.println("环绕开始：执行目标方法前，模拟事务开启");
		Object obj=pjp.proceed();
		System.out.println("环绕结束：执行目标方法后，模拟事务关闭");
		return obj;
	}
	/*
	 * 异常通知
	 */
	@AfterThrowing("myPointCut()")
	public void except(Throwable e) {
		System.out.println("异常通知：程序执行异常"+e.getMessage());
	}
	/*
	 * 后置通知
	 */
	@After("myPointCut()")
	public void after() {
		System.out.println("最终通知，模拟释放资源");
	}
}
