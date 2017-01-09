package org.codethink.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * 客户端与服务器进行通信的完整示例
 * https://my.oschina.net/leejun2005/blog/104955
 * 
 * @author CaiXiangNing
 * @date 2017年1月6日
 * @email caixiangning@gmail.com
 */
public class SocketServer1 {
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			/** 创建ServerSocket */
			// 创建一个ServerSocket在端口8080监听客户请求
			serverSocket = new ServerSocket(8080);
			System.out.println("服务器进程启动成功,等待客户端的连接!");
			while (true) {
				// 侦听并接受到此Socket的连接,请求到来则产生一个Socket对象，并继续执行
				Socket socket = serverSocket.accept();
				System.out.println("服务器接收来自" + socket.getInetAddress() + ":" + socket.getPort() + "的连接请求,连接成功,等待客户端发送消息!");
				// 由Socket对象得到输入流，并构造相应的BufferedReader对象
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// 由Socket对象得到输出流，并构造PrintWriter对象
				PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
				
				String msg = null;
				while((msg = bufferedReader.readLine()) != null){
					System.out.println("接收来自客户端的消息 : " + msg);
					printWriter.println("hello Client, I am Server!");
					printWriter.flush();
					if(msg.equals("bye")){
						break;
					}
				}

				/** 关闭Socket */
				printWriter.close();
				bufferedReader.close();
				socket.close();
				System.out.println("服务器断开来自" + socket.getInetAddress() + ":" + socket.getPort() + "的连接!");
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally {
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
