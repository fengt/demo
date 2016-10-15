package org.fengt.interf;

public class QQ implements Car, Product {

	@Override
	public void run() {
		System.out.println("启动出发！");
	}

	@Override
	public void stop() {
		System.out.println("刹车！");
	}

	@Override
	public double getPrice() {
		return 30000;
	}

}
