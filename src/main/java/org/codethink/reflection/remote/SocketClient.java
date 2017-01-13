package org.codethink.reflection.remote;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Test;

/**
 * 
 * 客户端代码
 * 
 * @author CaiXiangNing
 * @date 2017年1月12日
 * @email caixiangning@gmail.com
 */
public class SocketClient {
	
	public void invoke() throws Exception, IOException{
		Socket socket = new Socket("localhost", 8080);
		OutputStream out = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);
		
		InputStream in = socket.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(in);
		// 这里是new Class[]{int.class, int.class}而不是new Class[]{Integer.class, Integer.class}
		// Message msg = new Message("org.codethink.reflection.remote.CalculateService", "sub", new Class[]{double.class, double.class}, new Object[]{new Double(200.12), new Double(100.11)});
		// Message msg = new Message("org.codethink.reflection.remote.CalculateService", "getTime", new Class[]{}, new Object[]{});
		
		Message msg = new Message("org.codethink.reflection.remote.CalculateService", "add", new Class[]{int.class, int.class}, new Object[]{new Integer(100), new Integer(200)});
		oos.writeObject(msg);
		
		msg = (Message)ois.readObject();
		System.out.println("客户端接收的来自服务器处理后的结果对象:" + msg);
		
		ois.close();
		oos.close();
		socket.close();
	}
	
	/**
	 * 启动客户端并远程调用服务器端方法
	 * @throws IOException
	 * @throws Exception
	 */
	@Test
	public void testClient() throws IOException, Exception{
		new SocketClient().invoke();
	}
}
