package org.core1.collection;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This program sorts a set of items by comparing their descriptions.
 * @version 1.10 2015-01-08
 * @author Administrator
 *
 */
public class TreeSetTest {
	public static void main(String[] args){
		SortedSet<Item> parts = new TreeSet<Item>();
		parts.add(new Item("Toaster", 7234));
		parts.add(new Item("Widget", 4562));
		parts.add(new Item("Modem", 6912));
		System.out.println(parts);
		
		SortedSet<Item> sortByDescription = new TreeSet<Item>(new 
				Comparator<Item>(){
			public int compare(Item a, Item b){
				String descA = a.getDescription();
				String descB = b.getDescription();
				return descA.compareTo(descB);
			}
		});
		
		sortByDescription.addAll(parts);
		System.out.println(sortByDescription);
	}
	
}


// An item with a description and a part number.
class Item implements Comparable<Item>{
	
	/**
	 * Constructs an item.
	 * @param aDescription
	 * @param aPartNumber
	 */
	public Item(String aDescription, int aPartNumber){
		description = aDescription;
		partNumber = aPartNumber;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String toString(){
		return "[description= "+description+", partNumber="+partNumber+"]";
	}
	
	public boolean equals(Object otherObject){
		if(this == otherObject) return true;
		if(otherObject == null) return false;
		if(getClass() != otherObject.getClass()) return false;
		Item other = (Item)otherObject;
		return description.equals(other.description)
				&& partNumber == other.partNumber;
	}
	
	public int hasCode(){
		return 13 * description.hashCode() + 17 * partNumber;
	}
	
	public int compareTo(Item other){
		return partNumber - other.partNumber;
	}
	
	private String description;
	private int partNumber;
	
}
