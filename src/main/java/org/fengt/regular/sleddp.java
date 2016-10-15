package org.fengt.regular;

import java.util.concurrent.TimeUnit;

public class sleddp {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.out.println("11111----" + System.currentTimeMillis()/1000);
		TimeUnit.SECONDS.sleep(10);//make sure read-only database is synchronized
		System.out.println("22222----" + System.currentTimeMillis()/1000);
	}

}
