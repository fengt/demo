package org.fengt.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class ReadJsonUtil {

	public static void main(String[] args) {
		List<String> list = getJsonString();
		for (String json : list) {
			System.out.println(json);
//			AutoFlushVo autoFlush = JSON.parseObject(json, AutoFlushVo.class);
//			System.out.println(autoFlush.getWaybillno());
		}
	}

	
	public static List<String> getJsonString() {
		BufferedReader reader = null;
		String jsonStr = "";
		try {
			String filePath = "/Users/ftag/Downloads/postfile.json";
			
			FileInputStream fileInputStream = new FileInputStream(new File(filePath));
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				jsonStr = jsonStr + tempString;
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Arrays.asList(jsonStr.split(";"));
	}
}
