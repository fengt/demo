package org.fengt.net;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient extends Frame {
	
	private static final long serialVersionUID = 1L;
	
	Socket s = null;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	private boolean bConnected = false;
	
	TextField tfTxt = new TextField();
	TextArea taContent = new TextArea();
	
	Thread tRec = new Thread(new RecThread());
	
	public static void main(String[] args){
		new ChatClient().launchFrmae();
	}

	public void launchFrmae() {
		setLocation(400,300);
		this.setSize(300,300);
		add(tfTxt, BorderLayout.SOUTH);
		add(taContent, BorderLayout.NORTH);
		pack();
		this.setTitle("Online Chat");
		this.addWindowListener(new WindowAdapter(){

			public void windowClosing(WindowEvent e) {
				disconnect();
				System.exit(0);
			}
		});
		tfTxt.addActionListener(new TFListener());
		setVisible(true);
		connect();
		
		tRec.start();
		
	}
	
	public void connect(){
		try {
			s = new Socket("127.0.0.1", 8888);
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
			System.out.println("the server be connected");
			bConnected = true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect(){
		try {
			dos.close();
			dis.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*try {
			bConnected = false;
			tRec.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				dos.close();
				dis.close();
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
	}
	
	private class TFListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String str = tfTxt.getText().trim();
			//taContent.setText(str);
			tfTxt.setText("");
			try {
				
				dos.writeUTF(str);
				dos.flush();
				//dos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public class RecThread implements Runnable{

		public void run() {
				try {
					while(bConnected){
						String str = dis.readUTF();
						//System.out.println(str);
						taContent.setText(taContent.getText() + str +'\n');
					}
				} catch (SocketException e) {
					System.out.println("This Client is out!");
				} catch (IOException e) {
					e.printStackTrace();
			}
		}
		
	}
}



























