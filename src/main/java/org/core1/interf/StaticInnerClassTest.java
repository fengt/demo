package org.core1.interf;

/**
 * This program demonstrates the use of static inner classes.
 * @author Administrator
 *
 */
public class StaticInnerClassTest {
	public static void main(String[] args) {
		double[] d = new double[20];
		for(int i = 0; i < d.length; i++)
			d[i] = 100 * Math.random();
		ArrayAlg.Pair p = ArrayAlg.minmax(d);
		System.out.println("min=" + p.getFirst());
		System.out.println("max=" + p.getSecond());
	}
}

class ArrayAlg{
	public static Pair minmax(double[] values){
		double min = Double.MIN_VALUE;
		double max = Double.MAX_VALUE;
		for(double v : values){
			if(min > v) min = v;
			if(max < v) max = v;
		}
		return new Pair(min, max);	
	}
	
	public static class Pair{
		public Pair(double f, double s){
			first = f;
			second = s;
		}
		
		public double getFirst(){
			return first;
		}
		
		public double getSecond(){
			return second;
		}
		
		private double first;
		private double second;
	}
}
