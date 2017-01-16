package org.codethink.reflection.proxy.dynamic;

import org.junit.Test;

/**
 * 
 * 动态代理测试类
 * 
 * @author CaiXiangNing
 * @date 2017年1月16日
 * @email caixiangning@gmail.com
 */
public class DynamicProxyTest {
	/**
	 * 动态代理测试方法
	 */
	@Test
	public void testDynamicProxy(){
		// 创建接口委托类
		CalculateService calculateService = new CalculateServiceImpl();
		// 创建动态代理类实例
		CalculateService calculateServiceProxy = DynamicProxyFactory.getProxy(calculateService, CalculateService.class);
		System.out.println("动态代理类的名字是：" + calculateServiceProxy.getClass().getName());
		System.out.println("运行add(100, 200)程序运行结果：" + calculateServiceProxy.add(100, 200));
	}
}
