package org.codethink.reflection.proxy.statics;

import org.codethink.reflection.interfaces.CalculateService;
import org.codethink.reflection.interfaces.CalculateServiceImpl;
import org.junit.Test;

/**
 * 
 * 静态代理测试类
 * 
 * @author CaiXiangNing
 * @date 2017年1月16日
 * @email caixiangning@gmail.com
 */
public class StaticProxyTest {
	
	/**
	 * 静态代理测试方法
	 */
	@Test
	public void testStaticProxy(){
		// 创建委托类对象
		CalculateService calculateService = new CalculateServiceImpl();
		// 在代理类中注入委托类对象
		CalculateService calculateServiceProxy = new CalculateServiceProxy(calculateService);
		System.out.println("运行add(100, 200)程序运行结果：" + calculateServiceProxy.add(100, 200));
	}
}
