package org.core1.collection;

import java.util.BitSet;

/**
 * This program runs the Sieve of Erathostenses benchmark. It computes all primes up to 2,000,000.
 * @version 1.10
 * @author Administrator
 *
 */
public class Sieve {
	public static void main(String[] args){
		int n = 10;
		long start = System.currentTimeMillis();
		BitSet b = new BitSet(n);
		int count = 0;
		int i;
		for(i = 2; i <= n; i++)
			b.set(i);
		i = 2;
		while(i * i <= n){
			if(b.get(i)){
				count++;
				int k = i * 2;
				while(k <= n){
					b.clear(k);
					k += i;
				}
			}
			i++;
		}
		while(i <= n){
			if(b.get(i)) count++;
			i++;
		}
		long end = System.currentTimeMillis();
		System.out.println(count + "primes");
		System.out.println((end - start) + " milliseconds");
	}
}
