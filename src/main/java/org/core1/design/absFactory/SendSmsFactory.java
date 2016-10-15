package org.core1.design.absFactory;

public class SendSmsFactory implements Provider{
	@Override
	public Sender produce(){
		return new SmsSender();
	}
}
