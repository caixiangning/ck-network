package org.codethink.reflection;

/**
 * 
 * 用于测试反射机制的类
 * 
 * @author CaiXiangNing
 * @date 2017年1月12日
 * @email caixiangning@gmail.com
 */
public class Leaguer {
	private String name;
	private int age;
	
	public Leaguer() {
		super();
	}

	public Leaguer(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public int add(int param1, int param2){
		return param1 + param2;
	}
	
	public String echo(String msg){
		return "echo:" + msg;
	}

	@Override
	public String toString() {
		return "Leaguer [name=" + name + ", age=" + age + "]";
	}
}
