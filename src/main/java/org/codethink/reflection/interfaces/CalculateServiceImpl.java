package org.codethink.reflection.interfaces;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 服务器端服务接口委托类
 * 
 * @author CaiXiangNing
 * @date 2017年1月12日
 * @email caixiangning@gmail.com
 */
public class CalculateServiceImpl implements CalculateService{

	/**
	 * 加法实现方法
	 */
	public Integer add(int param1, int param2) {
		// TODO Auto-generated method stub
		return param1 + param2;
	}

	/**
	 * 获取当前时间接口实现方法
	 */
	public Date getTime() {
		// TODO Auto-generated method stub
		return Calendar.getInstance().getTime();
	}
}
