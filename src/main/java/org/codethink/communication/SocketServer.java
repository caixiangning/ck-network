package org.codethink.communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * https://my.oschina.net/leejun2005/blog/104955
 * 
 * @author CaiXiangNing
 * @date 2017年1月6日
 * @email caixiangning@gmail.com
 */
public class SocketServer {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			/** 创建ServerSocket */
			// 创建一个ServerSocket在端口8080监听客户请求
			ServerSocket serverSocket = new ServerSocket(8080);
			System.out.println("服务器端进程启动成功,等待客户端的连接!");
			while (true) {
				// 侦听并接受到此Socket的连接,请求到来则产生一个Socket对象，并继续执行
				Socket socket = serverSocket.accept();
				System.out.println("服务器端接收来自" + socket.getInetAddress() + ":" + socket.getPort() + "的连接请求,连接成功,等待客户端发送消息!");
				/** 接收客户端发送信息 */
				// 由Socket对象得到输入流，并构造相应的BufferedReader对象
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// 获取从客户端读入的字符串
				String result = bufferedReader.readLine();
				System.out.println("接收来自客户端的消息 : " + result);

				/** 向发送客户端信息 */
				// 由Socket对象得到输出流，并构造PrintWriter对象
				PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
				printWriter.print("hello Client, I am Server!");
				printWriter.flush();

				/** 关闭Socket */
				printWriter.close();
				bufferedReader.close();
				socket.close();
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
			// serverSocket.close();
		}
	}
}
