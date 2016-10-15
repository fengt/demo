package org.core1.design.dapter.inte;

public class WrapperTest {

	public static void main(String[] args) {
		Sourceable source1 = new SourceSub1();
		Sourceable source2 = new SourceSub1();
		
		source1.method1();
		source1.method2();
		source2.method1();
		source2.method2();
	}

}
