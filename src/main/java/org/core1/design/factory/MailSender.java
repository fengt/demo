package org.core1.design.factory;

public class MailSender implements Sender {

	@Override
	public void Send() {
		System.out.println("This is mailsender!");
	}

}
