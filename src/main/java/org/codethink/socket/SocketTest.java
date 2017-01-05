package org.codethink.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * 
 * 关于Socket对象的相关测试类
 * 
 * @author CaiXiangNing
 * @date 2017年1月5日
 * @email caixiangning@gmail.com
 */
public class SocketTest {
	
	
	/**
	 * 通过socket构造函数测试端口是否被服务器程序监听
	 * 带参数的Socket构造方法创建Socket对象时，会试图建立与服务器的连接，连接成功返回Socket对象，连接失败抛出异常
	 * 
	 */
	@Test
	public void testPortScanner(){
		String host = "localhost";
		
		Socket socket = null;
		for(int port=8080; port<8090; port++){
			try {
				socket = new Socket(host, port);
				System.out.println("该端口被服务器程序监听,端口是: " + port);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				System.out.println("无法解析该域名,未知主机异常!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("该端口无任何服务器程序监听,端口是: " + port);
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
	}
	
	/**
	 * 测试在指定超时时间内是否连接成功
	 *  public void connect(SocketAddress endpoint, int timeout) throws IOException
	 */
	@Test
	public void testConnectTimeOut(){
		Socket socket = new Socket();
		SocketAddress remoteAddress = new InetSocketAddress("localhost", 8080);
		int timeOut = 1; // 单位毫秒
		try {
			socket.connect(remoteAddress, timeOut);
			System.out.println("在指定超时时间内连接成功!");
		} catch(SocketTimeoutException e){
			System.out.println("在指定超时时间内连接失败!");
		}
		catch (IOException e) {
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
	
}
