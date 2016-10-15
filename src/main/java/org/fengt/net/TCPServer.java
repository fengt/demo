package org.fengt.net;
import java.io.IOException;
import java.net.*;
import java.io.*;

public class TCPServer {

	private static ServerSocket ss;

	public static void main(String[] args) throws IOException {
		ss = new ServerSocket(7777);
		while(true){
			Socket s = ss.accept();
			System.out.println("a Client be connected!");
			DataInputStream dis = new DataInputStream(s.getInputStream());
			System.out.println(dis.readUTF());
			dis.close();
			s.close();
		}
		

	}

}
