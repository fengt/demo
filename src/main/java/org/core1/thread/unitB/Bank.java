package org.core1.thread.unitB;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.10
 * @author Administrator
 *
 */
public class Bank {

	/**
	 * Constructs the Bank.
	 * @param n
	 * @param initialBalance
	 */
	public Bank(int n, double initialBalance) {
		accounts = new double[n];
		for(int i = 0; i < accounts.length; i++){
			accounts[i] = initialBalance;
		}
	}
	
	/**
	 * Transfer money from one account to another.
	 * @param from
	 * @param to
	 * @param amount
	 */
	public void transfer(int from, int to, double amount){
		bankLock.lock();
		try {
			if(accounts[from] < amount) return;
			System.out.print(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf(" %10.2f from %d to %d", amount, from, to);
			accounts[to] += amount;
			System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
		} finally {
			bankLock.unlock();
		}
	}
	
	/**
	 * Gets the sum of all account balances.
	 * @return
	 */
	public double getTotalBalance(){
		double sum = 0;
		for(double a : accounts){
			sum += a;
		}
		return sum;
	}
	
	/**
	 * Gets the number of accounts in the bank.
	 * @return
	 */
	public int size(){
		return accounts.length;
	}

	private Lock bankLock = new ReentrantLock();//ReentrantLock implements the Lock interface
	private final double[] accounts; 
}