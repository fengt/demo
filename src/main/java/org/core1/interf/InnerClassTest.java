package org.core1.interf;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class InnerClassTest {
	public static void main(String[] args) {
		TalkingClock clock = new TalkingClock(3000, true);
		clock.start();
		
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}
}


class TalkingClock{
	
	public TalkingClock(int interval, boolean beep){
		this.interval = interval;
		this.beep = beep;
	}
	
	public void start(){
		ActionListener listener = new TimePrinter2();
		Timer t = new Timer(interval, listener);
		t.start();
	}
	
	private int interval;
	private boolean beep;
	
	public class TimePrinter2 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Date now = new Date();
			System.out.println("At the tone, the time is " + now);
			if(beep) Toolkit.getDefaultToolkit().beep();
		}
	}
}
