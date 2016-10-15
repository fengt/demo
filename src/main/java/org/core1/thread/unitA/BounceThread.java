package org.core1.thread.unitA;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BounceThread {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				JFrame frame = new BounceFrame2();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class BallRunnable implements Runnable{
	public BallRunnable(Ball aBall, Component aComponent){
		ball = aBall;
		component = aComponent;
	}
	public void run(){
		try {
			for (int i = 1; i <= STEPS; i++) {
				ball.move(component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
		}
	}
	private Ball ball;
	private Component component;
	public static final int STEPS = 1000;
	public static final int DELAY = 5;
}


/**
 * The frame with ball component and buttons.
 * @author Administrator
 *
 */
class BounceFrame2 extends JFrame{
	
	/**
	 * Constructs the frame with the component for showing the bouncing ball and Start and Close buttons.
	 */
	public BounceFrame2(){
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("Bounce");
		
		comp = new BallComponent();
		add(comp, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start", new ActionListener(){
			public void actionPerformed(ActionEvent event){
				addBall();
			}
		});
		
		addButton(buttonPanel, "Close", new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Adds a button to a container.
	 * @param c
	 * @param title
	 * @param listener
	 */
	public void addButton(Container c, String title, ActionListener listener){
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	/**
	 * Add a bouncing ball to the panel and makes it bounce 1000 times.
	 */
	public void addBall(){
		Ball ball = new Ball();
		comp.add(ball);
		Runnable r = new BallRunnable(ball, comp);
		Thread t  = new Thread(r);
		t.start();
		/*try {
			Ball ball = new Ball();
			comp.add(ball);
			for (int i = 1; i < STEPS; i++) {
				ball.move(comp.getBounds());
				comp.paint(comp.getGraphics());
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
		}*/
	}
	
	private BallComponent comp;
	public static final int DEFAULT_WIDTH = 450;
	public static final int DEFAULT_HEIGHT = 350;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;
}

