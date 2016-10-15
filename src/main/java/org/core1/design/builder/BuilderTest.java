package org.core1.design.builder;

public class BuilderTest {
	
	public static void main(String[] args) {
		Builder builder = new Builder();
		builder.produceSmsSender(5);
		System.exit(0);
	}
}
