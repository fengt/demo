package org.core1.design.factory;

public class SendFactory {
	
	/**1、普通工厂*/
	/*public Sender produce(String type){
		if("mail".equals(type))
			return new MailSender();
		else if("sms".equals(type))
			return new SmsSender();
		else{
			System.out.println("请输入正确的类型！");
			return null;
		}
	}*/
	
	/**2、多个工厂*/
	/*public Sender produceMail(){
		return new MailSender();
	}
	
	public Sender produceSms(){
		return new SmsSender();
	}*/
	
	/**2、静态工厂*/
	public static Sender produceMail(){
		return new MailSender();
	}
	
	public static Sender produceSms(){
		return new SmsSender();
	}
}
