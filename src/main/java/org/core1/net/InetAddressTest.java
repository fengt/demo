package org.core1.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class InetAddressTest {

	public static void main(String[] args) {
		String[] s = {"github.com"};
		getAddress(s);
		getSocket();
	}
	
	public static void getAddress(String[] args){
		try {
			if(args.length > 0){
				String host = args[0];
				InetAddress[] address = InetAddress.getAllByName(host);
				for(InetAddress a : address)
					System.out.println(a);
			}else{
				InetAddress localHostAddress = InetAddress.getLocalHost();
				System.out.println(localHostAddress);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getSocket(){
		try {
			Socket s = new Socket("time-A.timefreq.bldrdoc.gov", 13);
			try{
				InputStream inStream = s.getInputStream();
				Scanner in = new Scanner(inStream);
				
				while(in.hasNext()){
					String line = in.nextLine();
					System.out.println(line);
				}
			} finally{
				s.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
