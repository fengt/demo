package org.fengt.regular;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegularTest {

	
	public static void fr(Object o){
		System.out.println(o);
	}
	public static void main(String[] args) {
		
		
		fr("aaa".matches("..."));
		fr("a2334b".replaceAll("\\d","-"));
		
		Pattern fr=Pattern.compile("[a-z]{3}");
		Matcher m=fr.matcher("aaa");
		fr(m.matches());
		
		
		//初步认识 . * +
		
		
		
		//初步认识 \s \S \w  \W
		fr("a_3".matches("\\w{3}"));
		fr("abc3232&^%".matches("[a-z]{3}\\d+[&^%*]+"));
		fr("\\".matches("\\\\"));
		
		
		//POSIX Style
		fr("a".matches("\\p{Lower}"));
		
		//boundary
		fr("hello fft".matches("^h.*"));
		fr("hello fft".matches("^.*ft$"));
		fr("hello fft".matches("^h[a-z]{1,3}o\\b.*"));
		fr("hellofft".matches("^h[a-z]{1,3}o\\b.*"));
		
		
		//white lines
		fr(" \n".matches("^[\\s&&[^\\n]]*\\n$"));
		
		//email地址
		fr("fengtao009@123.com".matches("[\\w[\\d-.]]+@[\\w[\\d-.]]+\\.[\\w[\\d-.]]+"));
		
		
		//matches  find lookingAt
		Pattern fr1=Pattern.compile("\\d{3,5}");
		Matcher m1=fr1.matcher("123-3232-23342-00");
		fr(m1.matches());
		m1.reset();
		fr(m1.find());
		fr(m1.start()+"-"+m1.end());
		fr(m1.find());
		fr(m1.find());
		fr(m1.find());
		//fr(m1.start()+"-"+m1.end());
		
		
		fr(m1.lookingAt());
		fr(m1.lookingAt());
		fr(m1.lookingAt());
		fr(m1.lookingAt());
		
		//replacement
		Pattern dr=Pattern.compile("java",Pattern.CASE_INSENSITIVE);
		Matcher m2=dr.matcher("java Java jAva jAVa jaVA IlovejAVA dfdfasdfad");
		//fr(m2.replaceAll("java"));
		StringBuffer buf=new StringBuffer();
		int i=0;
		while(m2.find()){
			i++;
			if(i%2==0){
				m2.appendReplacement(buf, "java");
			}
			else
				m2.appendReplacement(buf, "JAVA");
		}
		m2.appendTail(buf);
		fr(buf);
		
		//group
		Pattern f=Pattern.compile("(\\d{3,5})([a-z]{2})");
		Matcher m3=f.matcher("123aa-3232bb-23342cc-00");
		while(m3.find()){
			fr(m3.group(2));
		}
		
		//qulifiers
		Pattern p=Pattern.compile("(.{3,10})[0-9]");        //0-10
		//Pattern p=Pattern.compile("(.{3,10}+)[0-9]");    //not match!
		//Pattern p=Pattern.compile("(.{3,10}?)[0-9]");	   //0-5
		Matcher m4 = p.matcher("aaaa5bbbb6");
		if(m4.find())
			fr(m4.start()+"-"+m4.end());
		else
			fr("not match!");
		
		
		//货位编码匹配
		Pattern h = Pattern.compile("\\d{2}-[A-Z]{1}(-\\d{3}){0,2}");
		Matcher h1 = h.matcher("01");
		Matcher h2 = h.matcher("01-A-201");
		Matcher h3 = h.matcher("01-A-101-201");
		fr(h1.matches());
		fr(h2.matches());
		fr(h3.matches());
		
		//SN号匹配(LZ637AQ/1337LZ/G37)
//		String[] sn_regex = {"[A-Z]{2}\\d{3}.*", "\\d{4}.*", "[F-N]{1}\\d{2}.*"};
		
		//账号匹配(wzj<王自健>/100655<刘洁>)
//		String account_regex = "([a-z]{1,}|\\d{6})<.+>";
		
		String s = "\"213231ms231243\"";
		Pattern reg = Pattern.compile("^\".*\"$");
		if(reg.matcher(s).matches()){
			System.out.println(s.substring(1, s.length()-1));
		}
		
		
	}
}
