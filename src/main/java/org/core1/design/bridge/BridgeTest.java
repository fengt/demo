package org.core1.design.bridge;

public class BridgeTest {
	public static void main(String[] args) {
		Bridge bridge = new MyBridge();
		
		Sourceable source1 = new SouceSub1();
		bridge.setSource(source1);
		bridge.method();
		
		Sourceable source2 = new SouceSub2();
		bridge.setSource(source2);
		bridge.method();
	}
}
