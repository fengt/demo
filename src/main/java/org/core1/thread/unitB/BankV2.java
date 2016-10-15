package org.core1.thread.unitB;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A bank with a number of bank accounts that uses locks for serializing access.
 * @version 1.20
 * @author Administrator
 *
 */
public class BankV2 {

	/**
	 * Constructs the Bank.
	 * @param n
	 * @param initialBalance
	 */
	public BankV2(int n, double initialBalance) {
		accounts = new double[n];
		for(int i = 0; i < accounts.length; i++){
			accounts[i] = initialBalance;
		}
		bankLock = new ReentrantLock();
		sufficientFunds = bankLock.newCondition();
	}
	
	/**
	 * Transfer money from one account to another.
	 * @param from
	 * @param to
	 * @param amount
	 */
	public void transfer(int from, int to, double amount) throws InterruptedException{
		bankLock.lock();
		try {
//			if(accounts[from] < amount) return;
			while(accounts[from] < amount)
				sufficientFunds.await();
			System.out.print(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf(" %10.2f from %d to %d", amount, from, to);
			accounts[to] += amount;
			System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
			sufficientFunds.signalAll();
		} finally {
			bankLock.unlock();
		}
	}
	
	/**
	 * Gets the sum of all account balances.
	 * @return
	 */
	public double getTotalBalance(){
		bankLock.lock();
		try{
			double sum = 0;
			for(double a : accounts)
				sum += a;
			return sum;
		} finally{
			bankLock.unlock();
		}
	}
	
	/**
	 * Gets the number of accounts in the bank.
	 * @return
	 */
	public int size(){
		return accounts.length;
	}

	private Condition sufficientFunds;
	private Lock bankLock;
//	private Lock bankLock = new ReentrantLock();//ReentrantLock implements the Lock interface
	private final double[] accounts; 
}



