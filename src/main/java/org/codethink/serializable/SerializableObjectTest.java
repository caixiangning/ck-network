package org.codethink.serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

/**
 * 
 * 对象序列化和反序列化测试类(对象字节序列永久保存到硬盘上)
 * 对象序列化步骤：
	a、创建一个对象输出流，它可以包装一个其他类型的目标输出流，如文件输出流。
	b、通过对象输出流的writeObject方法写入对象。
       对象反序列化步骤：
	a、创建一个对象输入流，它可以包装任何一个其他类型的源输入流，如文件输出流。
	b、通过对象输入流的readObject方法读取对象。
 * 
 * @author CaiXiangNing
 * @date 2017年1月9日
 * @email caixiangning@gmail.com
 */
public class SerializableObjectTest {
	
	@Test
	public void testSerializableObject() throws FileNotFoundException, IOException, ClassNotFoundException{
		Leaguer leaguer1 = new Leaguer("cai1", 27);
		Leaguer leaguer2 = new Leaguer("cai2", 26);
		
		// 对象输出流
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\leaguer1.obj"));
		// 对象序列化
		out.writeObject(leaguer1);
		out.writeObject(leaguer2);
		out.close();
		
		// 对象输入流
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\leaguer1.obj"));
		// 对象反序列化
		Leaguer leaguerTmp1 = (Leaguer)in.readObject();
		Leaguer leaguerTmp2 = (Leaguer)in.readObject();
		System.out.println(leaguerTmp1);
		System.out.println(leaguerTmp2);
		in.close();
	}
}
