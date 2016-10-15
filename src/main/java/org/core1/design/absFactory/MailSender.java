package org.core1.design.absFactory;

public class MailSender implements Sender {

	@Override
	public void Send() {
		System.out.println("This is mailsender!");
	}

}
