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
 * 客户端
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
		
		Message msg = new Message("org.codethink.reflection.remote.CalculateService", "add", new Class[]{Integer.class, Integer.class}, new Object[]{new Integer(100), new Integer(200)});
		oos.writeObject(msg);
		
		msg = (Message)ois.readObject();
		System.out.println(msg.getResult());
		
		ois.close();
		oos.close();
		socket.close();
	}
	
	public static void main(String[] args) throws IOException, Exception{
		new SocketClient().invoke();
	}
}
