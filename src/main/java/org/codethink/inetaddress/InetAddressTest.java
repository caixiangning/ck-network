package org.codethink.inetaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * 
 * 测试网络地址相关类的使用
 * 
 * @author CaiXiangNing
 * @date 2017年1月3日
 * @email caixiangning@gmail.com
 */
public class InetAddressTest {
	
	/**
	 * 测试表示IP地址的InetAddress类，该类包括主机名和IP地址
	 */
	@Test
	public void testInetAddress(){
		try {
			String domainName = "www.baidu.com";
			InetAddress inetAddress = InetAddress.getByName(domainName);
			// www.baidu.com/61.135.169.125
			System.out.println("域名" + domainName + "详细信息：" + inetAddress);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试根据InetAddress类输出本机地址
	 */
	@Test
	public void testLocalAddress(){
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			// www.baidu.com/61.135.169.125
			System.out.println("本机地址详细信息：" + inetAddress);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
