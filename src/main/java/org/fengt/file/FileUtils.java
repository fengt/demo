package org.fengt.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.zeroturnaround.zip.FileSource;
import org.zeroturnaround.zip.ZipEntrySource;


public class FileUtils {

	/**
	 * 遍历目录excel文件，添加数组
	 * @param fileList
	 * @param path
	 * @return
	 */
	public static List<File> getExcelFilesByPath(List<File> fileList, String path) {

		File file = new File(path);
		String suffix = "";
		if (!file.isDirectory()) {

			suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1, 
					file.getName().length());
			if ("xlsx".equals(suffix) || "xls".equals(suffix)) {
				fileList.add(file);
			}

		} else if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readFile = new File(path + "/" + filelist[i]);
				if (!readFile.isDirectory()) {
					
					suffix = readFile.getName().substring(readFile.getName().lastIndexOf(".") + 1, 
							readFile.getName().length());
					if ("xlsx".equals(suffix) || "xls".equals(suffix)) {
						fileList.add(readFile);
					}
				} else if (readFile.isDirectory()) {
					getExcelFilesByPath(fileList, path + "/" + filelist[i]);
				}
			}
		}
		return fileList;
	}
	
	
	public static ZipEntrySource[] getZipEntrySource(String filePath){
		List<File> fileList = new ArrayList<File>();
    	getExcelFilesByPath(fileList, filePath);
    	if(fileList.size() > 0){
    		ZipEntrySource[] zipSource = new ZipEntrySource[fileList.size()];
    		for (int i = 0; i < fileList.size(); i++) {
    			zipSource[i] = new FileSource(fileList.get(i).getName(), fileList.get(i));
			}
    		return zipSource;
    	}
        
    	return null;
	}
	
}
