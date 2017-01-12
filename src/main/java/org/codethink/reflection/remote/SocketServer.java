package org.codethink.reflection.remote;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 服务器端
 * 
 * @author CaiXiangNing
 * @date 2017年1月12日
 * @email caixiangning@gmail.com
 */
public class SocketServer {
	// 存放远程对象的缓存
	private Map<String, Object> mapCache = new HashMap<String, Object>();
	
	/**
	 * 将一个服务对象放在缓存中
	 * @param className
	 * @param obj
	 */
	public void register(String className, Object obj){
		mapCache.put(className, obj);
	}
	
	/**
	 * 服务器启动服务并且执行调用方法过程
	 * @throws IOException
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public void service() throws IOException, Exception{
		ServerSocket serverSocket = new ServerSocket(8080);
		System.out.println("服务器启动!");
		while(true){
			Socket socket = serverSocket.accept();
			InputStream in = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(in);
			
			OutputStream out = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
			
			// 接收客户端发送的消息对象并进行反序列化
			Message msg = (Message)ois.readObject();
			System.out.println("服务器接收的客户端发送的消息对象是:" + msg);
			
			// 使用反射调用服务器的服务并生成返回结果
			msg = this.invoke(msg);
			// 向客户端发送包含了执行结果的消息对象
			oos.writeObject(msg);
			
			// 关闭相关流和socket
			ois.close();
			oos.close();
			socket.close();
		}
	}
	
	/**
	 * 根据通信信息对象包含的信息在服务器端调用相关方法并返回包含结果的通信信息对象
	 * @param msg
	 * @return
	 * @throws Exception 
	 */
	public Message invoke(Message msg) throws Exception{
		Object result = null;
		try{
			Class<?> clazz = Class.forName(msg.getClassName());
			Method method = clazz.getMethod(msg.getMethodName(), msg.getParamTypes());
			
			// 从缓存中取出要调用的远程对象
			Object obj = this.mapCache.get(msg.getClassName());
			if(obj == null){
				throw new Exception("远程服务器中不存在调用类型的对象!");
			}
			else{
				result = method.invoke(obj, msg.getParams());
			}
			msg.setResult(result);
			System.out.println("远程服务器调用服务后返回的结果对象是" + msg);
			return msg;
		} catch(Exception e){
			result = e;
			System.out.println("抛出异常!");
		}
		
		msg.setResult(result);
		return msg;
	}
	
	
	/**
	 * 启动服务器并注册服务
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, Exception{
		SocketServer socketServer = new SocketServer();
		socketServer.register("org.codethink.reflection.remote.CalculateService", new CalculateServiceImpl());
		socketServer.service();
	}
}
