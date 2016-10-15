package org.fengt.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * IO工具类
 * @author fengtao
 *
 */
public class IOUtils {
	
	public static void copy01(String src, String dest) throws IOException{
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dest);
		int b;
		while((b=in.read()) != -1){
			out.write(b);
		}
		in.close();
		out.close();
	}
	
	public static void copy02(String src, String dest) throws IOException{
		InputStream in = new BufferedInputStream(
				new FileInputStream(src));
		OutputStream out = new BufferedOutputStream(
				new FileOutputStream(dest));
		int b;
		while((b=in.read()) !=-1){
			out.write(b);
		}
		in.close();
		out.close();
	}
	
	public static void copy03(String src, String dest) throws IOException{
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dest);
		byte[] buf = new byte[1024*512];
		int b;
		while((b=in.read(buf)) != -1){
			out.write(buf, 0, b);
		}
		in.close();
		out.close();
	}
	
	public static void printHex(byte[] ary){
		int i=1;
		for(byte b: ary){
			int x = b;
			x &= 0xff;
			if(x <= 0xf){
				System.out.println("0");
			}
			System.out.print(Integer.toHexString(x)+" ");
			if(i++%10==0){
				System.out.println();
			}
			System.out.println();
		}
	}
	
	public static byte[] read(String filename) throws IOException{
		File file = new File(filename);
		/*按照文件长度创建byte数组*/
		byte[] buf = new byte[(int)file.length()];
		/*打开文件*/
		FileInputStream in = new FileInputStream(file);
		/**
		 * 读取文件，尽可能多的读取流中的数据，填充到buf;
		 * 返回值size是读取的数量
		 */
		int size = in.read(buf);
		System.out.println(size);
		in.close();
		return buf;
	}
	
	/**重载方法**/
	public static byte[] read(File file) throws IOException{
		byte[] buf = new byte[(int)file.length()];
		FileInputStream in = new FileInputStream(file);
		int size = in.read(buf);
		System.out.println(size);
		in.close();
		return buf;
	}
	
}
