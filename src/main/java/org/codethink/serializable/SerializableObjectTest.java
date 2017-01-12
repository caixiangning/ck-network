package org.codethink.serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * 
 * 对象序列化和反序列化测试类
 * 对象序列化步骤：
	a、创建一个对象输出流，它可以包装一个其他类型的目标输出流，如文件输出流。
	b、通过对象输出流的writeObject方法写入对象。
       对象反序列化步骤：
	a、创建一个对象输入流，它可以包装任何一个其他类型的源输入流，如文件输出流。
	b、通过对象输入流的readObject方法读取对象。
 * 
 * @author CaiXiangNing
 * @date 2017年1月9日
 * @email caixiangning@gmail.com
 */
public class SerializableObjectTest {
	
	/**
	 * 测试对象字节序列永久保存到硬盘上,然后再根据该文件创建对象
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testSerializableObject() throws FileNotFoundException, IOException, ClassNotFoundException{
		Leaguer leaguer1 = new Leaguer("cai1", 27);
		Leaguer leaguer2 = new Leaguer("cai2", 26);
		
		// 对象输出流
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\leaguer.obj"));
		// 对象序列化
		out.writeObject(leaguer1);
		out.writeObject(leaguer2);
		out.close();
		
		// 对象输入流
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\leaguer.obj"));
		// 对象反序列化
		Leaguer leaguerTmp1 = (Leaguer)in.readObject();
		Leaguer leaguerTmp2 = (Leaguer)in.readObject();
		System.out.println(leaguerTmp1);
		System.out.println(leaguerTmp2);
		in.close();
	}
	
	/**
	 * 测试在网络上传送对象的字节序列的服务器端
	 */
	@Test
	public void testSerializableServer(){
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8080);
			while(true){
				Socket socket = serverSocket.accept();
				Leaguer leaguer = new Leaguer("cai1", 27);
				System.out.println("服务器将要向客户端发送的对象是：" + leaguer);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(leaguer);
				out.close();
				socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 测试在网络上传送对象的字节序列的客户端
	 */
	@Test
	public void testSerializableClient(){
		Socket socket = null;
		try {
			socket = new Socket("localhost", 8080);
			InputStream in = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(in);
			Object obj = ois.readObject();
			System.out.println("客户端接收到服务器传送的对象：" + obj);
			ois.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
