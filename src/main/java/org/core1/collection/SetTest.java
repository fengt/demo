package org.core1.collection;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This program uses a set to print all unique words in System.in.
 * @version 1.10 2015-01-08
 * @author Administrator
 *
 */
public class SetTest {
	public static void main(String[] args){
//		Set<String> words = new HashSet<String>();
//		long totalTime = 0;
//		
//		Scanner in = new Scanner(System.in);
//		while(in.hasNext()){
//			String word = in.next();
//			long callTime = System.currentTimeMillis();
//			words.add(word);
//			callTime = System.currentTimeMillis() - callTime;
//			totalTime += callTime;
//		}
//		
//		Iterator<String> iter = words.iterator();
//		for (int i = 1; i < 20; i++) {
//			System.out.println(iter.next());
//			System.out.println("...");
//			System.out.println(words.size() + " distinct words. " + totalTime + " milliseconds.");
//		}
		
		SortedSet<String> sorter = new TreeSet<String>();
		sorter.add("Erica");
		sorter.add("Amy");
		sorter.add("Carl");
		
		for(String s : sorter) System.out.println(s);
	}
}
