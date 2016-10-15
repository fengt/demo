package org.core1.thread.unitB;

public class UnsynchBankTest {

	/**
	 * This program shows data corruption when multiple threads access a data structure.
	 * @param args
	 */
	public static void main(String[] args){
//		Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
		BankV3 b = new BankV3(NACCOUNTS, INITIAL_BALANCE);
		int i;
		for(i = 0; i < NACCOUNTS; i++){
			TransferRunnable r = new TransferRunnable(b, i, 2*INITIAL_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
	}
	
	public static final int NACCOUNTS = 10;
	public static final double INITIAL_BALANCE = 1000;
}
