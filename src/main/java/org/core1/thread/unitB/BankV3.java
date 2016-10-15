package org.core1.thread.unitB;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A bank with a number of bank accounts that uses synchronization primitives.
 * @version 1.30
 * @author Administrator
 *
 */
public class BankV3 {

	/**
	 * Constructs the Bank.
	 * @param n
	 * @param initialBalance
	 */
	public BankV3(int n, double initialBalance) {
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
	public synchronized void transfer(int from, int to, double amount) throws InterruptedException{
		while(accounts[from] < amount)
			wait();
		System.out.print(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf(" %10.2f from %d to %d", amount, from, to);
		accounts[to] += amount;
		System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
		notifyAll();
	}
	
	/**
	 * Gets the sum of all account balances.
	 * @return
	 */
	public synchronized double getTotalBalance(){
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

	private final double[] accounts; 
}