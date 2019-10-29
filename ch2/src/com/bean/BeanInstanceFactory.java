package com.bean;

public class BeanInstanceFactory {
	
	public BeanClass createBeanClassFactory() {
		
		return new BeanClass("调用实例工厂方法实例化bean");
		
	}
}
