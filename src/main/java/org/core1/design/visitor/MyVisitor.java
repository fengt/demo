package org.core1.design.visitor;

public class MyVisitor implements Visitor {

	@Override
	public void visitor(Subject sub) {
		System.out.println("visitor the subject: "+ sub.getSubject());
	}

}
