package org.core1.design.visitor;

public interface Subject {
	public void accept(Visitor visitor);
	public String getSubject();
}
