package org.fengt.interview;

import java.util.Scanner;

public class DayCounts {
	public static void main(String[] args) {
		int dayOfMonth[] = {31,28,31,30,31,30,31,31,30,31,30,31};
		int year=0, month=0, day=0;
		int dayCount = 0;//天数
		boolean flag = true;
		
		while(flag){
			Scanner date = new Scanner(System.in);
			System.out.println("请输入年月日，格式为：年 月 日");
			year = date.nextInt();
			month = date.nextInt();
			day = date.nextInt();
			if(IsWrong(year,month,day))
				System.out.println("日期输入有误，请重新输入！");
			else{
				flag = false;
				date.close();
			}
		}
		
		if(IsLeapYear(year)) dayOfMonth[1] = 29;
		
		for (int i = 0; i <= month - 2; i++) {
			dayCount += dayOfMonth[i];
		}
		dayCount += day;
		System.out.println("这一天是这一年的第"+ dayCount +"天。");
	}
	
	
	public static boolean IsWrong(int year, int month, int day){
		int dayOfMonth[] = {31,28,31,30,31,30,31,31,30,31,30,31};
		if(year < 1) return true;
		if(IsLeapYear(year)) dayOfMonth[1] = 29;
		if(month > 12 || month < 1) return true;
		if(day > 0 && day <= dayOfMonth[month - 1]) return false;
		else return true;
	}
	
	
	public static boolean IsLeapYear(int year){
		if(year%4 == 0 && year%100 !=0 || year%400 == 0)
			return true;
		else
			return false;
	}
}
