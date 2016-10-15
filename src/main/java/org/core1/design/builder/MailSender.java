package org.core1.design.builder;

public class MailSender implements Sender {

	@Override
	public void Send() {
		System.out.println("This is mailsender!");
	}

}
