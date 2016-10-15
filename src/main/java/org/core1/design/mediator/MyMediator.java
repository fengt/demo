package org.core1.design.mediator;

public class MyMediator implements Mediator {

	private User1 user1;
	private User2 user2;
	
	@Override
	public void createMediator() {
		this.user1 = new User1(this);
		this.user2 = new User2(this);
	}

	@Override
	public void workAll() {
		user1.work();
		user2.work();
	}

}
