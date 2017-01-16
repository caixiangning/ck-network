package org.codethink.reflection.proxy.statics;

import java.util.Date;

/**
 * 
 * 服务器端服务接口
 * 
 * @author CaiXiangNing
 * @date 2017年1月12日
 * @email caixiangning@gmail.com
 */
public interface CalculateService {
	/**
	 * 加法接口
	 * @param param1
	 * @param param2
	 */
	public Integer add(int param1, int param2);
	
	/**
	 * 获取当前时间接口
	 * @return
	 */
	public Date getTime();
}
