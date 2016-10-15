package org.core1.design.template;

public class StrategyTest {
	public static void main(String[] args) {
		String exp = "7+8";
		AbstractCalculator cal = new Plus();
		int result = cal.calculate(exp, "\\+");
		System.out.println(result);
	}
}
