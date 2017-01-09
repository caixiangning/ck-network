package org.codethink.multiserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * 
 * 多线程服务器处理与客户端的连接
 * 使用JDK提供的线程池
 * 
 * @author CaiXiangNing
 * @date 2017年1月6日
 * @email caixiangning@gmail.com
 */
public class MultiServer2Test {
	
	// 服务器端代码
	class Server {
		private ServerSocket serverSocket;
		private ExecutorService executorService; // 线程池
		private final int POOL_SIZE = 4; // 单个CPU时线程池中工作线程数目
		
		// 构建监听本机指定端口的ServerSocket服务器对象
		public Server(int port) throws IOException {
			// TODO Auto-generated constructor stub
			// 限定服务器请求队列为3来模仿处理大规模客户端请求阻塞
			this.serverSocket = new ServerSocket(port, 3);
			// 创建线程池
			executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
			System.out.println("服务器端进程启动成功!");
		}

		// 监听客户端请求并输出客户端请求ip和端口号
		public void service() {
			while (true) {
				Socket socket = null;
				try {
					// 从连接队列请求中取出一个连接
					socket = serverSocket.accept();
					// System.out.println("服务器端接收新连接" + socket.getInetAddress() + ":" + socket.getPort());
					executorService.execute(new SocketHandler(socket));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// 这只是与单个客户端通信遇到的异常，可能是由于客户端过早断开连接引起的，这种异常不应该中断整个while循环
					e.printStackTrace();
				} finally {
					if (socket != null) {
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

	// 负责与单个客户端进行通信的线程类
	class SocketHandler implements Runnable{
		private Socket socket;
		public SocketHandler(Socket socket) {
			// TODO Auto-generated constructor stub
			this.socket = socket;
		}
		
		
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("服务器端接收新连接" + socket.getInetAddress() + ":" + socket.getPort());
			// 模拟服务器处理请求，时间2秒
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 启动服务器进程，监听指定端口
	 */
	@Test
	public void startServer() {
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
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void startClient() throws InterruptedException {
		
		Socket[] sockets = new Socket[10];
		for (int i = 0; i < 10; i++) {
			try {
				sockets[i] = new Socket("localhost", 8080);
				System.out.println("第" + (i + 1) + "次连接成功!");
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("第" + (i + 1) + "次连接失败!");
			}
		}
	}
}
