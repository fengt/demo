package org.fengt.thread;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class UnsyncWriterDemo {

	private static PrintWriter out;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException{
		final LinkedList<String> buf = new LinkedList<String>();
		out = new PrintWriter(new FileOutputStream("unsync.txt"));
		
		final Thread writer = new Thread(){
			public void run(){
				while(true){
					if(buf.isEmpty()){
						try {
							out.flush();
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						continue;
					}
					String str = buf.removeFirst();
					out.println(str);
				}
			}
		};
		
		writer.setDaemon(true);
		
		final Thread reader = new Thread(){
			private Scanner s;

			public void run(){
				s = new Scanner(System.in);
				while(true){
					String str = s.nextLine();
					buf.addLast(str);
					if(str.equalsIgnoreCase("Q")){
						writer.interrupt();
						break;
					}
				}
			}
		};
		
		//启动线程
		reader.start();
		writer.start();
	}

}
