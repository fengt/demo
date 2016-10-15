package org.fengt.thread;

public class ThreadDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person1 p1 = new Person1();
		Person2 p2 = new Person2();
		Person3 p3 = new Person3();
		p3.setPriority(Thread.MAX_PRIORITY);//10
		p1.setPriority(Thread.MIN_PRIORITY);//1
		p1.start();
		p2.start();
		p3.start();
		System.out.println("main Over!");
		
	}
}

class Person1 extends Thread{
	public void run(){
		for(int i = 0; i < 10; i++){
			System.out.println("你是谁呀！");
			Thread.yield();
		}
		System.out.println("##########你是谁呀！Over");
	}
}

class Person2 extends Thread{
	public void run(){
		for(int i = 0; i < 10; i++){
			System.out.println("修理水管的！");
			Thread.yield();
		}
		System.out.println("###########修理水管的！Over");
	}
}

class Person3 extends Thread{
	public void run(){
		for(int i = 0; i < 10; i++){
			System.out.println("弹弓！");
			Thread.yield();
		}
		System.out.println("#########弹弓！Over");
	}
}




