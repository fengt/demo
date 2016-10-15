package org.fengt.thread;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
	public static void main(String[] args) {
		/*创建定时器对象*/
		final Timer timer = new Timer();
		
		/*设置日期*/
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
//		Date date = cal.getTime();
		
		/*设置任务*/
		timer.schedule(new TimerTask(){
			public void run(){
				System.out.println("爆炸！");
			}
		}, 5000);
		
		/*取消定时器*/
		timer.schedule(new TimerTask(){
			public void run(){
				timer.cancel();
			}
		}, 3000);
		
		//countDown();
		
	}
	
	/*倒计时*/
	public static void countDown(){
		int min = 1;
		long start = System.currentTimeMillis();
		final long end = start + min*60*1000;
		
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				long show = end - System.currentTimeMillis();
				long h = show/1000/60/60;
				long m = show/1000/60%60;
				long s = show/1000%60;
				System.out.println(h+":"+m+":"+s);
			}
		}, 0,1000);
		
		/*计时结束后，停止timer计时任务*/
		timer.schedule(new TimerTask(){
			public void run(){
				timer.cancel();
			}
		}, new Date(end));
	}
	
}
