package org.codethink.reflection.proxy.remote;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.RemoteException;

/**
 * 
 * 创建动态代理类及其实例
 * 
 * @author CaiXiangNing
 * @date 2017年1月17日
 * @email caixiangning@gmail.com
 */
public class ProxyFactory {
	
	/**
	 * 返回动态代理创建的实例
	 * @param <T>
	 * @param clazz
	 * @param host
	 * @param port
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getProxy(final Class<T> clazz, final String host, final int port){
		InvocationHandler handler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				Connector connector = null;
				try{
					connector = new Connector(host, port);
					Message message = new Message(clazz.getName(), method.getName(), method.getParameterTypes(), args);
					connector.send(message);
					message = (Message)connector.receive();
					Object result = message.getResult();
					if(result instanceof Throwable){
						throw new RemoteException(result.toString());
					}
					else{
						return result;
					}
				} finally{
					if(connector != null){
						connector.close();
					}
				}
			}
		};
		return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, handler);
	}
}
