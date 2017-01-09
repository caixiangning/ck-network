package org.codethink.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/**
 * 
 * 
 * 
 * @author CaiXiangNing
 * @date 2017年1月6日
 * @email caixiangning@gmail.com
 */
public class ServerSocketTest {

	// 服务器端代码
	class SocketServer {
		private ServerSocket serverSocket;

		// 构建监听本机指定端口的ServerSocket服务器对象
		public SocketServer(int port) throws IOException {
			// TODO Auto-generated constructor stub
			// 限定服务器请求队列为3来模仿处理大规模客户端请求阻塞
			this.serverSocket = new ServerSocket(port, 3);
			System.out.println("服务器端进程启动成功!");
		}

		// 监听客户端请求并输出客户端请求ip和端口号
		public void service() throws IOException {
			/** 创建ServerSocket*/
      // 创建一个ServerSocket在端口监听客户请求
      while (true) {
          // 侦听并接受到此Socket的连接,请求到来则产生一个Socket对象，并继续执行
          Socket socket = serverSocket.accept();

          /** 接受客户端发送的消息 */
          // 由Socket对象得到输入流，并构造相应的BufferedReader对象
          BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
          // 获取从客户端读入的字符串
          String result = null;
          while((result = bufferedReader.readLine())!=null){
          	 System.out.println("Client say : " + result);

             /** 向客户端发送消息 */
             // 由Socket对象得到输出流，并构造PrintWriter对象
             PrintWriter printWriter =new PrintWriter(socket.getOutputStream());
             printWriter.print("hello Client, I am Server!");
             printWriter.flush();
             if(result.equals("bye")){
            	 /** 关闭Socket*/
               printWriter.close();
            	 break;
             }
          }
          bufferedReader.close();
          socket.close();
      }
		}
	}

	// 客户端代码
	class SocketClient {
		private Socket socket;

		// 构建监听本机指定端口的ServerSocket服务器对象
		public SocketClient(String host, int port) throws IOException {
			// TODO Auto-generated constructor stub
			this.socket = new Socket(host, port);
			System.out.println("客户端进程启动成功!");
		}

		public void sendMsg() {
			try {
				while(true){
					/** 向服务器端发送消息 */
					// 由Socket对象得到输出流，并构造PrintWriter对象
					PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
					// 将输入读入的字符串输出到Server
					BufferedReader sysBuff = new BufferedReader(new InputStreamReader(System.in));
					printWriter.println(sysBuff.readLine());
					// 刷新输出流，使Server马上收到该字符串
					printWriter.flush();
					
					/** 接受服务器端发送的消息 */
					// 由Socket对象得到输入流，并构造相应的BufferedReader对象
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					// 输入读入一字符串
					String result = bufferedReader.readLine();
					System.out.println("Server say : " + result);

					printWriter.close();
					bufferedReader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// 这只是与单个客户端通信遇到的异常，可能是由于客户端过早断开连接引起的，这种异常不应该中断整个while循环
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

	/**
	 * 启动服务器进程，监听指定端口
	 */
	@Test
	public void startServer() {
		try {
			// 创建服务器对象
			SocketServer socketServer = new SocketServer(8080);
			// 启动服务
			socketServer.service();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 启动客户端进程，与服务器建立连接
	 * 
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	@Test
	public void startClient() {
		SocketClient socketClient;
		try {
			socketClient = new SocketClient("localhost", 8080);
			socketClient.sendMsg();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
