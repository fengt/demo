package org.fengt.interf;

public class QQDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QQ qq = new QQ();
		Product p = qq;
//		Car car = qq;
		System.out.println(p.getPrice());
		qq.run();
		qq.stop();
		
		Car c = (Car)p;//强制转换
		c.run();
		c.stop();
	}

}
