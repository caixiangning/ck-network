package org.codethink.serializable;

import java.io.Serializable;

/**
 * 
 * 待序列化或反序列化的类
 * 只有实现了Serializable接口的类的对象才能被序列化。
 * 否则在调用ObjectOutputStream对象的writeObject方法时会抛出NotSerializableException异常。
 * 
 * @author CaiXiangNing
 * @date 2017年1月9日
 * @email caixiangning@gmail.com
 */
@SuppressWarnings("serial")
public class Leaguer implements Serializable {
	private String name;
	private int age;

	public Leaguer(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Leaguer [name=" + name + ", age=" + age + "]";
	}
}
