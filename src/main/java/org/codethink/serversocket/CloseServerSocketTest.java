package org.codethink.serversocket;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.Test;

/**
 * 
 * 扫描1~65535之间的端口号，如果ServerSocket成功创建，则该端口未被其他服务器进程绑定，
 * 否则说明该端口已经被其他进程占用
 * 
 * @author CaiXiangNing
 * @date 2017年1月5日
 * @email caixiangning@gmail.com
 */
public class CloseServerSocketTest {
	@Test
	public void testCloseServerSocket(){
		for(int port=1; port<=6553; port++){
			try {
				ServerSocket serverSocket = new ServerSocket(port);
				// 及时关闭ServerSocket
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("端口" + port + "已经被其他服务器进程占用!");
				// e.printStackTrace();
			}
		}
	}
}

