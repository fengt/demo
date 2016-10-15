package org.core1.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This program demonstrates the random shuffle and sort algorithms.
 * @version 1.10 2015-01-09
 * @author Administrator
 *
 */
public class ShuffleTest {
	public static void main(String[] args){
		List<Integer> number = new ArrayList<Integer>();
		for(int i = 1; i <= 49; i++)
			number.add(i);
		System.out.println(number);
		Collections.shuffle(number);
		System.out.println(number);
		List<Integer> winningCombination = number.subList(0, 6);
		Collections.sort(winningCombination);
		System.out.println(winningCombination);
	}
}
