package org.fengt.thread;

public class SyncDemo {
	/*public static void main(String[] args) {
		Table table = new Table();
		Person p1 = table.new Person();
		Person p2 = table.new Person();
		p1.start();
		p2.start();
	}*/
	
	public static void main(String[] args) {
		final Foo f = new Foo();
		Thread t = new Thread(){
			public void run(){
				f.add();
			}
		};
		t.start();
		f.add(2);
	}

}


class Table{
	int beans = 20;
	public synchronized int getBean(){
		if(beans == 0) throw new RuntimeException("没了！");
		Thread.yield();
		return beans--;
	}
	class Person extends Thread{
		public void run(){
			while(true){
				int bean = getBean();
				System.out.println(getName() + "," + bean);
				Thread.yield();
			}
		}
	}
}





class Foo{
	int a = 0;
	
	public synchronized void add(){
		System.out.println("Call add()");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		a++;
		System.out.println("Over call add()");
	}
	
	public synchronized void add(int b){
		System.out.println("Call add(b)!");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		a+=b;
		System.out.println("Over call add(b)!");
	}
}










