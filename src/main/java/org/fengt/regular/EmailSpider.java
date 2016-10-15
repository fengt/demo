package org.fengt.regular;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailSpider {

	private static BufferedReader br;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("E:\\document\\拿证名单(网络安全).xls"));
		String line="";
		while((line=br.readLine())!=null){
			parse(line);
		}

	}

	private static void parse(String line) {
		Pattern p=Pattern.compile("[\\w[\\d-.]]+@[\\w[\\d-.]]+\\.[\\w[\\d-.]]+");
		Matcher m=p.matcher(line);
		while(m.find()){
			System.out.println(m.group());
		}
	}

}
