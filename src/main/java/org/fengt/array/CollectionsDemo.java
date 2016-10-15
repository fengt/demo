package org.fengt.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsDemo {
	public static void main(String[] args) {
		List<String> names = new ArrayList<String>();
		names.add("Tom");
		names.add("Jerry");
		names.add("Black");
		names.add("Andy");
		names.add("Lee");
		//1、排序
		Collections.sort(names);
		System.out.println(names);
		//2、二分查找
		int index = Collections.binarySearch(names, "Andy");
		System.out.println(index);
		//3、乱序
		Collections.shuffle(names);
		System.out.println(names);
	}
}
