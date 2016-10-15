package org.fengt.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class CopyDemo {
	
	public static void main(String[] args) throws Exception{
		//CopyMethod();
		//CharacterTest();
		ReadOrWriteIO();
	}
	
	public static void CopyMethod(){
		try {
			long start = System.currentTimeMillis();
//			IOUtils.copy01("e:/001.ppt", "f:/002.ppt");
//			IOUtils.copy02("e:/001.ppt", "f:/002.ppt");
			IOUtils.copy03("e:/a.flv", "f:/b.flv");
			long end = System.currentTimeMillis();
			System.out.println("复制了一遍！"+(end-start)/1000);
		} catch (IOException e) {
			System.out.println("文件复制失败:"+e.getMessage());
		}
	}
	
	public static void CharacterTest(){
		 String s = "AB中";
		 try {
			 byte[] utf16be = s.getBytes("utf-16be");
			 System.out.println(Arrays.toString(utf16be));
			 IOUtils.printHex(utf16be);
			 byte[] utf8 = s.getBytes("utf-8");
			 System.out.println(utf8);
			 byte[] gbk = s.getBytes("gbk");
			 IOUtils.printHex(gbk);
			 
			 String str = new String(utf8,"utf-8");
			 System.out.println(str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static void ReadOrWriteIO(){
		String file = "e:/test.txt";
		try {
			/*写出文本*/
//			FileOutputStream out = new FileOutputStream(file);
			FileOutputStream out = new FileOutputStream(file,true);//文件中追加信息
			String str = "abc测试文本信息";
			byte[] utf8 = str.getBytes("utf-8");
//			out.write(utf8);
			out.write(utf8, 0, 9);
			out.flush();//清理缓冲区（尽可能写）
			out.close();
			/*读取文本*/
			byte[] buf = IOUtils.read(file);
			String s = new String(buf,"utf-8");
			String s2 = new String(buf,"gbk");
			System.out.println(s);
			System.out.println(s2);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
}
