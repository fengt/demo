package org.core1.design.dapter.cla;

public class AdapterTest {
	
	public static void main(String[] args) {
		Targetable target = new Adapter();
		target.method1();
		target.method2();
	}
}
