package org.fengt.thread;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocal {

	public static void main(String[] args){
		Some s1 = SomeFactory.getSome();
		Some s2 = SomeFactory.getSome();
		System.out.println(s1);
		System.out.println(s2);
	}
}

class Some{}

class SomeFactory1{
	public static Some getSome(){
		return new Some();
	}
}

/**
 * 模拟线程单例
 * @author ftag
 *
 */
class SomeFactory{
	private static Map<Long, Some> map = new HashMap<Long, Some>();
	
	public static Some getSome(){
		long threadId = Thread.currentThread().getId();
		Some some = map.get(threadId);
		if(some == null){
			some = new Some();
			map.put(threadId, some);
		}
		return some;
	}
}

/**
 * 使用ThreadLocal
 * @author ftag
 *
 */
//class SomeFactory{
//	private static ThreadLocal<Some> t1 = new ThreadLocal<Some>();
//	
//	public static Some getSome(){
//		Some some = t1.get();
//		if(some == null){
//			some = new Some();
//			t1.set(Some);
//		}
//		return some;
//	}
//}

