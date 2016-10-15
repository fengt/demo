package org.core1.design.absFactory;

public class FactoryTest {

	public static void main(String[] args) {
		Provider provider = new SendSmsFactory();
		Sender sender = provider.produce();
		sender.Send();
	}
}
