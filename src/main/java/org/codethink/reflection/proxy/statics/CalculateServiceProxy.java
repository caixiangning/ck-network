package org.codethink.reflection.proxy.statics;

import java.util.Date;

import org.codethink.reflection.interfaces.CalculateService;

/**
 * 
 * 服务器端服务接口代理类
 * 代理类是通过调用委托类的相关方法来提供特定服务的
 * 
 * @author CaiXiangNing
 * @date 2017年1月16日
 * @email caixiangning@gmail.com
 */
public class CalculateServiceProxy implements CalculateService{

	// 被代理的服务对象
	private CalculateService calculateService;
	
	// 注入被代理服务对象
	public CalculateServiceProxy(CalculateService calculateService) {
		super();
		this.calculateService = calculateService;
	}

	public Integer add(int param1, int param2) {
		// TODO Auto-generated method stub
		// 预处理
		System.out.println("before calling add!");
		// 调用被代理的calculateService实例的add方法
		int result = calculateService.add(param1, param2);
		// 事后处理
		System.out.println("after calling add!");
		return result;
	}

	public Date getTime() {
		// TODO Auto-generated method stub
		// 预处理
		System.out.println("before calling getTime!");
		// 调用被代理的calculateService实例的getTime方法
		Date result = calculateService.getTime();
		// 事后处理
		System.out.println("after calling getTime!");
		return result;
	}
}
