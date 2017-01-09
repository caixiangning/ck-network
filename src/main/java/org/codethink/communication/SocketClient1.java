package org.codethink.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
public class SocketClient1 {
	
	public static void main(String[] args) {
		Socket socket = null;
		try {
			/** 创建Socket */
			// 创建一个流套接字并将其连接到指定 IP地址的指定端口号(本处是本机)
			socket = new Socket("127.0.0.1", 8080);
			System.out.println("客户端端进程启动成功,并成功连接服务器,准备向服务器发送消息!");
			// 60s超时
			socket.setSoTimeout(60000);
			
			/** 向服务器端发送消息 */
			// 由Socket对象得到输出流，并构造PrintWriter对象
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			// 将输入读入的字符串输出到Server
			BufferedReader sysBuff = new BufferedReader(new InputStreamReader(System.in));
			/** 接收服务器端发送的消息 */
			// 由Socket对象得到输入流，并构造相应的BufferedReader对象
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg = null;
			while((msg = sysBuff.readLine()) != null){
				
				printWriter.println(msg);
				// 刷新输出流，使Server马上收到该字符串
				printWriter.flush();
				
				// 输入读入一字符串
				String result = bufferedReader.readLine();
				System.out.println("接收来自服务器的消息 : " + result);
				
				if(msg.equals("bye")){
					break;
				}
			}
			
			printWriter.close();
			sysBuff.close();
			bufferedReader.close();
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} finally{
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("客户端断开与服务器之间的连接!");
			}
		}
	}
}
