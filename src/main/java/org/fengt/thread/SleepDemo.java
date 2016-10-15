package org.fengt.thread;

public class SleepDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Thread t1 = new Thread(){
			public void run(){
				for(int i=0; i<2; i++){
					System.out.println("去睡觉了！");
					try{
						Thread.sleep(5000);
					}catch(InterruptedException e){
						e.printStackTrace();
						System.out.println("干嘛了，破相了！");
						break;
					}
				}
			}
		};
		t1.start();
		
		Thread t2 = new Thread(){
			public void run(){
				for(int i=0; i<2; i++){
					System.out.println("砸墙，咣当!");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("终于砸穿了！");
				t1.interrupt();
			}
		};
		t2.start();
		System.out.println("main Over");
	}

}
