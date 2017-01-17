package org.codethink.reflection.proxy.remote;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 
 * 负责建立客户端与远程服务器的连接，以及接收和发送Socket对象
 * 
 * @author CaiXiangNing
 * @date 2017年1月17日
 * @email caixiangning@gmail.com
 */
public class Connector {
	private String host;
	private int port;
	private Socket socket;
	private InputStream is;
	private ObjectInputStream ois;
	private OutputStream os;
	private ObjectOutputStream oos;
	
	public Connector(String host, int port) throws Exception{
		super();
		this.host = host;
		this.port = port;
		connect(host, port);
	}
	
	// 发送对象
	public void send(Object obj) throws Exception{
		oos.writeObject(obj);
	}
	
	// 接收对象
	public Object receive() throws Exception{
		return ois.readObject();
	}
	
	// 建立与远程服务器的连接
	public void connect() throws Exception{
		connect(host, port);
	}
	
	// 建立与远程服务器的连接
	public void connect(String host, int port) throws Exception{
		socket = new Socket(host, port);
		os = socket.getOutputStream();
		oos = new ObjectOutputStream(os);
		is = socket.getInputStream();
		ois = new ObjectInputStream(is);
	}
	
	// 关闭连接
	public void close(){
		try{
			
		} finally{
			try{
				ois.close();
				oos.close();
				socket.close();
			} catch(Exception e){
				System.out.println("关闭与服务器之间的连接");
			}
		}
	}
}
