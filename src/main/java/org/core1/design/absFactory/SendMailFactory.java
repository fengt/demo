package org.core1.design.absFactory;

public class SendMailFactory implements Provider{
	@Override
	public Sender produce(){
		return new MailSender();
	}
}
