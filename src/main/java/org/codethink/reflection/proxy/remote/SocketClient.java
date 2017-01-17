package org.codethink.reflection.proxy.remote;

import java.io.IOException;

import org.codethink.reflection.interfaces.CalculateService;
import org.junit.Test;

/**
 * 
 * 客户端代码
 * 
 * @author CaiXiangNing
 * @date 2017年1月12日
 * @email caixiangning@gmail.com
 */
public class SocketClient {
	
	/**
	 * 启动客户端并远程调用服务器端方法
	 * @throws IOException
	 * @throws Exception
	 */
	@Test
	public void testClient() throws IOException, Exception{
		CalculateService calculateService = ProxyFactory.getProxy(CalculateService.class, "localhost", 8080);
		System.out.println(calculateService.add(100, 200));
	}
}
