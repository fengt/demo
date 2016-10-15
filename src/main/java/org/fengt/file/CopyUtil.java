package org.fengt.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyUtil {

	public void copy(String src, String des) throws IOException{
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(des);
		byte[] buf = new byte[1024 * 512];
		int b;
		while((b = in.read(buf)) != -1){
			out.write(b);
		}
		in.close();
		out.close();
	}
}
