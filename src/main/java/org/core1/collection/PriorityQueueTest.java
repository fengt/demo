package org.core1.collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;

/**
 * This program demonstrates the use of a priority queue.
 * @version 1.10 2015-01-08
 * @author Administrator
 *
 */
public class PriorityQueueTest {
	public static void main(String[] args){
		PriorityQueue<GregorianCalendar> pq = new PriorityQueue<GregorianCalendar>();
		pq.add(new GregorianCalendar(1906, Calendar.DECEMBER, 9));
		pq.add(new GregorianCalendar(1815, Calendar.DECEMBER, 10));
		pq.add(new GregorianCalendar(1903, Calendar.DECEMBER, 3));
		pq.add(new GregorianCalendar(1910, Calendar.JUNE, 22));
		
		System.out.println("Iterating over elements...");
		for(GregorianCalendar date : pq){
			System.out.println(date.get(Calendar.YEAR));
		}
		System.out.println("Removing elements...");
		while(!pq.isEmpty()){
			System.out.println(pq.remove().get(Calendar.YEAR));
		}
	}
}
