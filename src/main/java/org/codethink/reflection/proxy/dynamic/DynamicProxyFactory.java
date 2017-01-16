package org.codethink.reflection.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * 动态代理类(使用了泛型，可适用于其他所有接口)
 * 
 * @author CaiXiangNing
 * @date 2017年1月16日
 * @email caixiangning@gmail.com
 */
public class DynamicProxyFactory {
	
	@SuppressWarnings("unchecked")
	public static <T> T getProxy(final T t, Class<?> clazz){
		// 创建InvocationHandler对象
		InvocationHandler handler = new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("before calling " + method);
				Object result = method.invoke(t, args);
				System.out.println("after calling " + method);
				return result;
			}
		};
		// public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces, InvocationHandler h)
		// 该静态方法负责创建动态代理类的实例，其中loader指定动态代理类的类加载器，
		// 参数interfaces指定动态代理类需要实现的所有接口，参数handler指定与动态代理类关联的InvocationHandler对象
		return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, handler);
	}
}
