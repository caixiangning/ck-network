package org.codethink.reflection.remote;

/**
 * 
 * 客户端与服务器通信的信息封装成的类
 * 
 * @author CaiXiangNing
 * @date 2017年1月12日
 * @email caixiangning@gmail.com
 */
public class Message {
	// 类名/服务接口名
	private String className;
	// 调用的方法名称
	private String methodName;
	// 方法参数类型
	private Class<?>[] paramTypes;
	// 方法参数值
	private Object[] params;
	
	// 表示远程调用结果，方法正常执行，返回结果;方法异常，返回异常信息
	private Object result;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(String className, String methodName, Class<?>[] paramTypes, Object[] params) {
		super();
		this.className = className;
		this.methodName = methodName;
		this.paramTypes = paramTypes;
		this.params = params;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getClassName() {
		return className;
	}

	public String getMethodName() {
		return methodName;
	}

	public Class<?>[] getParamTypes() {
		return paramTypes;
	}

	public Object[] getParams() {
		return params;
	}
}
