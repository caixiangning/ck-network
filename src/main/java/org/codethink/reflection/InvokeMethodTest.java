package org.codethink.reflection;

import java.lang.reflect.Method;

import org.junit.Test;

/**
 * 
 * 测试在运行中调用任意对象的方法
 * 
 * @author CaiXiangNing
 * @date 2017年1月11日
 * @email caixiangning@gmail.com
 */
public class InvokeMethodTest {
	
	/**
	 * 测试在运行时调用任意一个对象的方法
	 * @throws Exception
	 */
	@Test
	public void testInvokeMethod() throws Exception{
		Class<Leaguer> classType = Leaguer.class;
		// 通过类的不带参数的构造方法来创建一个类的对象
		Object obj = classType.newInstance(); 
		
		/**  调用对象的add()方法     */
		// Class类的getMethod(String name, Class<?>... parameterTypes)方法用于获得类的特定方法
		// name参数指定方法的名称，parameterTypes参数指定方法的参数类型
		Method addMethod = classType.getMethod("add", new Class[]{int.class, int.class});
		// Method类的invoke(Object obj, Object... args)方法用于动态执行一个对象的特定方法
		// 第一个obj参数指定具有该方法的对象，第二个参数args指定调用该方法时传递的参数
		Object result = addMethod.invoke(obj, new Object[]{new Integer(100), new Integer(200)});
		System.out.println("调用add(100,200)方法的结果是：" + (Integer)result);
		
		/**  调用对象的echo()方法     */
		Method echoMethod = classType.getMethod("echo", new Class[]{String.class});
		result = echoMethod.invoke(obj, new Object[]{"Hello"});
		System.out.println("调用echo(Hello)方法的结果是：" + (String)result);
	}
}
