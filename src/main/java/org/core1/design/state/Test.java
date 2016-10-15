package org.core1.design.state;

public class Test {
	public static void main(String[] args) {
		
		State state = new State();
		Context cnt = new Context(state);
		
		//设置第一种状态
		state.setValue("state1");
		cnt.method();
		
		//设置第二种状态
		state.setValue("state2");
		cnt.method();
	}
}
