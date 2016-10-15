package org.fengt.algorithm;

import java.util.Arrays;

public class SortDemo {
	public static void main(String[] args) {
		int[] ary = {8,2,3,7,1,21,22,32,9,10,43,18,19,10,9};
//		ary = KindsOfSort.bubbleSort(ary);
//		ary = KindsOfSort.selectionSort(ary);
//		ary = KindsOfSort.insertSort(ary);
		Arrays.sort(ary);
		System.out.println(Arrays.toString(ary));
	}
}
