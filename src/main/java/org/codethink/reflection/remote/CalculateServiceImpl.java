package org.codethink.reflection.remote;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 服务器端服务接口实现类
 * 
 * @author CaiXiangNing
 * @date 2017年1月12日
 * @email caixiangning@gmail.com
 */
public class CalculateServiceImpl implements CalculateService{

	/**
	 * 加法实现方法
	 */
	public int add(int param1, int param2) {
		// TODO Auto-generated method stub
		return param1 + param2;
	}

	/**
	 * 减法实现方法
	 */
	public double sub(double param1, double param2) {
		// TODO Auto-generated method stub
		return param1 - param2;
	}

	/**
	 * 获取当前时间接口实现方法
	 */
	public Date getTime() {
		// TODO Auto-generated method stub
		return Calendar.getInstance().getTime();
	}
}
