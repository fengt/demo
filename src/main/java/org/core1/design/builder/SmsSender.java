package org.core1.design.builder;

public class SmsSender implements Sender {

	@Override
	public void Send() {
		System.out.println("This is sms sender!");
	}

}
