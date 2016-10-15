package org.core1.thread.unitC;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.print("Enter base directory (e.g. /usr/local/mysql):)");
		String directory = in.nextLine();
		System.out.print("Enter keyword (e.g. volatile):");
		String keyword = in.nextLine();
		
		final int FILE_QUEUE_SIZE = 10;
		final int SEARCH_THREADS = 100;
		
		BlockingQueue<File> queue = new ArrayBlockingQueue<File>(FILE_QUEUE_SIZE);
		
		FileEnumerationTask enumerator = new FileEnumerationTask(queue, new File(directory));
		new Thread(enumerator).start();
		for(int i = 1; i <= SEARCH_THREADS; i++)
			new Thread(new SearchTask(queue, keyword)).start();
	}
}

/**
 * This task enumerates all files in a directory and its subdirectories.
 * @author Administrator
 *
 */
class FileEnumerationTask implements Runnable{
	
	/**
	 * Constructs a FileEnumerationTask.
	 * @param queue
	 * @param startingDirectory
	 */
	public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory){
		this.queue = queue;
		this.startingDirectory = startingDirectory;
	}
	
	public void run(){
		try {
			enumerate(startingDirectory);
			queue.put(DUMMY);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Recursively enumerates all files in a given directory and its subdirectories.
	 * @param directory
	 * @throws InterruptedException
	 */
	public void enumerate(File directory) throws InterruptedException{
		File[] files = directory.listFiles();
		for(File file : files){
			if(file.isDirectory()) enumerate(file);
			else queue.put(file);
		}
	}
	
	public static File DUMMY = new File("");
	
	private BlockingQueue<File> queue;
	private File startingDirectory;
}

/**
 * This task searches files for a given keyword.
 * @author Administrator
 *
 */
class SearchTask implements Runnable{
	
	/**
	 * Constructs a SearchTask.
	 * @param queue
	 * @param keyword
	 */
	public SearchTask(BlockingQueue<File> queue, String keyword){
		this.queue = queue;
		this.keyword = keyword;
	}
	
	public void run(){
		try {
			boolean done = false;
			while(!done){
				File file = queue.take();
				if(file == FileEnumerationTask.DUMMY){
					queue.put(file);
					done = true;
				}else search(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e){
		}
	}
	
	/**
	 * Searches a file for a given keyword and prints all matching lines.
	 * @param file
	 * @throws IOException
	 */
	public void search(File file) throws IOException{
		Scanner in = new Scanner(new FileInputStream(file));
		int lineNumber = 0;
		while(in.hasNext()){
			lineNumber++;
			String line = in.nextLine();
			if(line.contains(keyword)) System.out.printf("%s:%d:%s%n", file.getPath(),
					lineNumber, line);
		}
		in.close();
	}
	
	private BlockingQueue<File> queue;
	private String keyword;
}





