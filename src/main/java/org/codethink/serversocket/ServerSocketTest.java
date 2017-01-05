package org.codethink.serversocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * 
 * 
 * 
 * @author CaiXiangNing
 * @date 2017年1月5日
 * @email caixiangning@gmail.com
 */
public class ServerSocketTest {
	
	/**
	 * 测试服务器监听客户端请求，并设定客户端连接请求队列的长度(客户端)
	 * @throws InterruptedException
	 */
	@Test
	public void testBacklogClent() throws InterruptedException{
		int length = 20;
		String host = "localhost";
		int port = 8080;
		
		// 每隔1秒向服务器端发出共10次请求
		Socket[] sockets = new Socket[length];
		for(int i=0; i<20; i++){
			try {
				sockets[i] = new Socket(host, port);
				System.out.println("第"+ (i+1) +"次连接成功!");
				Thread.sleep(1000);
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("第"+ (i+1) +"次连接失败!");
				e.printStackTrace();
			} finally{
				if(sockets[i] != null){
					try {
						sockets[i].close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * 测试服务器监听客户端请求，并设定客户端连接请求队列的长度(服务器端)
	 * @throws InterruptedException
	 */
	@Test
	public void testBacklogServer() throws InterruptedException{
		int port = 8080;
		// 设定客户端连接请求的队列长度为3
		int backlog = 3;
		
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port, backlog);
			System.out.println("服务器端进程启动成功!");
			// 每隔10秒服务器端进程接收一个客户端连接
			while(true){
				Socket socket = null;
				try {
					socket = serverSocket.accept();
					System.out.println("服务器端接收新连接" + socket.getInetAddress() + ":" + socket.getPort());
					Thread.sleep(3000);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					if(socket != null){
						try {
							socket.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			if(serverSocket != null){
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
		
	
}
