package org.core1.thread.unitC;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTest {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.print("Enter base directory (e.g. /usr/local/jdk1.8.0/src):)");
		String directory = in.nextLine();
		System.out.print("Enter keyword (e.g. volatile):");
		String keyword = in.nextLine();
		
		MathCounter counter = new MathCounter(new File(directory), keyword);
		FutureTask<Integer> task = new FutureTask<Integer>(counter);
		Thread t = new Thread(task);
		t.start();
		
		try {
			System.out.println(task.get() + " matching files.");
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e){
		}
	}
}


class MathCounter implements Callable<Integer>{
	
	/**
	 * Constructs a MathCounter.
	 * @param directory
	 * @param keyword
	 */
	public MathCounter(File directory, String keyword){
		this.directory = directory;
		this.keyword = keyword;
	}
	
	public Integer call(){
		count = 0;
		try {
			File[] files = directory.listFiles();
			ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
			
			for(File file : files)
				if(file.isDirectory()){
					MathCounter counter = new MathCounter(file, keyword);
					FutureTask<Integer> task = new FutureTask<Integer>(counter);
					results.add(task);
					Thread t = new Thread(task);
					t.start();
				}else{
					if(search(file)) count++;
				}
			
			for(Future<Integer> result : results)
				try {
					count += result.get();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
		} catch (InterruptedException e) {
		}
		return count;
	}
	
	public boolean search(File file){
		try {
			Scanner in = new Scanner(new FileInputStream(file));
			boolean found = false;
			while(!found & in.hasNextLine()){
				String line = in.nextLine();
				if(line.contains(keyword)) found = true;
			}
			in.close();
			return found;
		} catch (IOException e) {
			return false;
		}
	}
	
	private File directory;
	private String keyword;
	private int count;
}
