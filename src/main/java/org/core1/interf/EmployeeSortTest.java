package org.core1.interf;

import java.util.Arrays;

public class EmployeeSortTest {
	public static void main(String[] args) {
		Employee1[] staff = new Employee1[3];
		
		staff[0] = new Employee1("FENG", 7000);
		staff[1] = new Employee1("Tao", 5000);
		staff[2] = new Employee1("Lee", 6000);
		
		Arrays.sort(staff);
		for(Employee1 e : staff)
			System.out.println("name=" + e.getName() + ", salary=" + e.getSalary());
	}
}


class Employee1 implements Comparable<Employee1>{
	public Employee1(String n, double s){
		name = n;
		salary = s;
	}
	
	public String getName() {
		return name;
	}
	public double getSalary() {
		return salary;
	}
	
	public void raiseSalary(double byPercent){
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	public int compareTo(Employee1 other){
		if(salary < other.salary) return -1;
		if(salary > other.salary) return 1;
		return 0;
	}

	private String name;
	private double salary;
}

