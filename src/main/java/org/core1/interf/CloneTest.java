package org.core1.interf;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This program demonstrates cloning.
 * @since Cay Horstmann
 * @author Administrator
 *
 */
public class CloneTest {

	public static void main(String[] args){
		Employee original = new Employee("FENG", 5000);
		original.setHireDay(2014, 6, 23);
		try {
			Employee copy = original.clone();
			copy.raiseSalary(10);
			copy.setHireDay(2013, 1, 1);
			System.out.println("original=" + original);
			System.out.println("copy=" + copy);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	
}


class Employee implements Cloneable{
	
	public Employee(String n, double s){
		name = n;
		salary = s;
		hireDay = new Date();
	}
	
	public Employee clone() throws CloneNotSupportedException{
		//call Object.clone()
		Employee cloned = (Employee)super.clone();
		//clone mutable fields
		cloned.hireDay = (Date)hireDay.clone();
		return cloned;
	}
	
	public void setHireDay(int year, int month, int day){
		Date newHireDay = new GregorianCalendar(year, month-1, day).getTime();
		//Example of instance field mutable
		hireDay.setTime(newHireDay.getTime());
	}
	
	public void raiseSalary(double byPercent){
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	public String toString(){
		return "Employee[name=" + name + ", salary=" + salary + ", hireDay=" + hireDay + "]";
	}
	
	private String name;
	private double salary;
	private Date hireDay;
}