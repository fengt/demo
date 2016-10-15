package org.core1.design.bridge;

public class MyBridge extends Bridge {

	public void method(){
		getSource().method();
	}
}
