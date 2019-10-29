package com.bean;

public class BeanStaticFactory {
	
	private static BeanClass beanInstance = new BeanClass("调用静态工厂方法实例化bean");
	
	public static BeanClass createInstance() {
		return beanInstance;
	}
}
