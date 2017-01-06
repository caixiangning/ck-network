package org.codethink.serversocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * 
 * 单线程服务器通信流程
 * 
 * @author CaiXiangNing
 * @date 2017年1月5日
 * @email caixiangning@gmail.com
 */
public class SingleServerTest {
	// 服务器端代码
	class Server{
		private ServerSocket serverSocket;
		// 构建监听本机指定端口的ServerSocket服务器对象
		public Server(int port) throws IOException {
			// TODO Auto-generated constructor stub
			this.serverSocket = new ServerSocket(port);
			System.out.println("服务器端进程启动成功!");
		}
		// 监听客户端请求并输出客户端请求ip和端口号
		public void service(){
			while(true){
				Socket socket = null;
				try {
					// 从连接队列请求中取出一个连接
					socket = serverSocket.accept();
					System.out.println("服务器端接收新连接" + socket.getInetAddress() + ":" + socket.getPort());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// 这只是与单个客户端通信遇到的异常，可能是由于客户端过早断开连接引起的，这种异常不应该中断整个while循环
					e.printStackTrace();
				} finally {
					if(socket != null){
						try {
							// 与一个客户端的通信结束，要关闭Socket
							socket.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	/**
	 * 启动服务器进程，监听指定端口
	 */
	@Test
	public void startServer(){
		try {
			// 创建服务器对象
			Server server = new Server(8080);
			// 启动服务
			server.service();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 启动客户端进程，与服务器建立连接
	 * @throws InterruptedException
	 */
	@Test
	public void startClient() throws InterruptedException{
		try {
			Socket[] sockets = new Socket[10];
			for(int i=0; i<10; i++){
				sockets[i] = new Socket("localhost", 8080);
				System.out.println("第"+ (i+1) +"次连接成功!");
				Thread.sleep(1000);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

