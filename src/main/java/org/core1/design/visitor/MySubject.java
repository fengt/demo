package org.core1.design.visitor;

public class MySubject implements Subject {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitor(this);
	}

	@Override
	public String getSubject() {
		return "love";
	}

}
