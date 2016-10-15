package org.core1.design.interpreter;

public class Test {
	public static void main(String[] args) {
		
		//计算9+3-5的值
		int result = new Minus().interpret(new Context(new Plus().interpret(new Context(9, 3)), 5));
		System.out.println(result);
	}
}
