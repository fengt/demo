package org.core1.design.factory;

public class FactoryTest {

	public static void main(String[] args) {
		Sender sender = SendFactory.produceMail();
		sender.Send();
	}
}
