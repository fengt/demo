package org.core1.thread.unitB;

public class TransferRunnable implements Runnable {

	/**
	 * Constructs a transfer runnable.
	 * @param b
	 * @param from
	 * @param max
	 */
	public TransferRunnable(BankV3 b, int from, double max){
		bank = b;
		fromAccount = from;
		maxAmount = max;
	}
	
	public void run(){
		try {
			while(true){
				int toAccount = (int)(bank.size() * Math.random());
				double amount = maxAmount * Math.random();
				bank.transfer(fromAccount, toAccount, amount);
				Thread.sleep((int)(DELAY * Math.random()));
			}
		} catch (InterruptedException e) {
		}
	}

	private BankV3 bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY = 10;
}
