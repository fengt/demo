package org.fengt.net;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	boolean started = false;
	ServerSocket ss = null;
	
	List<Client> clients = new ArrayList<Client>();
	
	public static void main(String[] args) {		
		new ChatServer().start();
	}
	
	public void start(){		
		try {
			ss = new ServerSocket(8888);
			started = true;
		}catch (SocketException e) {
			System.out.println("the Port is using...");
			System.out.println("Please close other relative proceduce.");
			System.exit(0);
		}catch (IOException e) { 
			e.printStackTrace();
		}
		try{
			while(started){
				Socket s = ss.accept();
				Client c = new Client(s);
				System.out.println("a client connected");
				new Thread(c).start();
				clients.add(c);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class Client implements Runnable{
		private Socket s;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		private boolean bConnected = false;
		
		public Client(Socket s){
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				bConnected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		public void send(String str){
			try {
				dos.writeUTF(str);
			} catch (IOException e) {
				clients.remove(this);
				System.out.println("Other Clients is out of the server's List.");
				//e.printStackTrace();
			}
		}
		
		public void run() {
			try{
				while(bConnected){
					String str = dis.readUTF();
					System.out.println(str);
					for(int i=0; i<clients.size(); i++){
						Client c = clients.get(i);
						c.send(str);
					}
					//下面两种遍历方法需要schnauzed(锁定)，效率不高
					/*for(Iterator<Client> it = clients.iterator(); it.hasNext(); ){
						Client c = it.next();
						c.send(str);
					}*/
					
					/*Iterator<Client> it = clients.iterator();
					while(it.hasNext()){
						Client c = it.next();
						c.send(str);
					}*/
					
				}
			}catch (EOFException e) {
					System.out.println("Devices closed or may be out of control.");
				}catch (IOException e) {
					e.printStackTrace();
				}finally{
					try {
						if(dis != null) dis.close();					
						if(dos != null) dos.close();
						if(s != null) s.close();
					}catch (IOException e) {
						e.printStackTrace();
				}
			}
		}
	}
}
