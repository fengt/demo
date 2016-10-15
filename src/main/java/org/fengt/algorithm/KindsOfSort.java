package org.fengt.algorithm;

public class KindsOfSort {
	
	/**
	 * 选择排序
	 * @param ary
	 * @return
	 */
	public static int[] selectionSort(int[] ary){
		for(int i=0; i<ary.length-1; i++){
			for(int j=i+1; j<ary.length; j++){
				if(ary[i]>ary[j]){
					int temp = ary[j];
					ary[j] = ary[i];
					ary[i] = temp;
				}
			}
		}
		return ary;
	}
	
	/**
	 * 冒泡排序
	 * @param ary
	 * @return
	 */
	public static int[] bubbleSort(int[] ary){
		for(int i=0; i<ary.length-1; i++){
			for(int j=0; j<ary.length-i-1; j++){
				if(ary[j]>ary[j+1]){
					int temp = ary[j];
					ary[j] = ary[j+1];
					ary[j+1] = temp;
				}
			}
		}
		return ary;
	}
	
	/**
	 * 插入排序
	 * @param ary
	 * @return
	 */
	public static int[] insertSort0(int[] ary){
		int i,j,temp;
		for (i = 1; i < ary.length; i++) {
			temp = ary[i];
			for(j=i-1; j>=0; j--){
				if(temp<ary[j]){
					ary[j+1] = ary[j];
				}else{
					break;//找到插入位置
				}
			}
			ary[j+1] = temp;
		}
		return ary;
	}
	
	/**
	 * 插入排序(经典写法)
	 * @param ary
	 * @return
	 */
	public static int[] insertSort(int[] ary){
		int i,j,temp;
		for (i = 1; i < ary.length; i++) {
			temp = ary[i];
			for(j=i-1; j>=0 && temp<ary[j]; j--){
				ary[j+1] = ary[j];
			}
			ary[j+1] = temp;
		}
		return ary;
	}
}
