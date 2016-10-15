package org.fengt.regular;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test1 {
	private Graphics myG;
	private Font font;
	
	public static void main(String[] args) throws Exception {
		//draw(g);
		//getTime();
//		Timestamp t = new Timestamp(System.currentTimeMillis());dateBiz.java
//		System.out.println(t);
//		System.out.println(System.currentTimeMillis()/1000/60);
//		testFor2();
//		classTest();
//		strSplit();
//		subString("LZ637AQ");
//		dateTest();
//		dateTest2();
//		testZeroNegate();
//		testNum();
//		testEnum();
//		testArrayToString();
//		testTwoDateCompare();
//		testSS();
//		testStr();
//		timestamp2Date();
//		expressCompanyId();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println(getDistanceOfTwoDate(sdf.parse("2016-10-11 17:55:01"), sdf.parse("2016-10-12 00:00:00")));
	} 
	
	public static void expressCompanyId(){
		String target = "山东潍坊高密市村村通货运信息咨询服务中心结";
		
		String[] str = new String[]{
				"山东枣庄滕州市荆河多拉快跑物流服务部结算公司",
				"山东潍坊高密市村村通货运信息咨询服务中心结算公司",
				"山东聊城临清市青年经一路创辉货物配送服务结算公司"
		};
		
		for (String sub : str) {
			System.out.println(target.length());
			if(target.equals(sub.substring(0, 21))){
				target = sub;
				System.out.println(target);
			}
		}
	}
	
	public static void timestamp2Date() throws ParseException{
		String timestamp = String.valueOf(20160425568l).substring(0, 8);
		String timestamp2 = String.valueOf(160425101512104816l);
		String sub = timestamp2.substring(0, 6);
		String twenty = "20";
		
		
		String REG_yyyyMMdd = "^[0-9]{4}(((0[13578]|(10|12))(0[1-9]|[1-2][0-9]|3[0-1]))|(02(0[1-9]|[1-2][0-9]))|((0[469]|11)(0[1-9]|[1-2][0-9]|30)))$";
		Pattern yyyyMMdd_PATTERN = Pattern.compile(REG_yyyyMMdd);
		Matcher matcher = yyyyMMdd_PATTERN.matcher(timestamp);
//		
//		Calendar calendar = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(df2.format(df.parse(twenty.concat(sub))));
		
		if(matcher.matches()){
			System.out.println(df2.format(df.parse(timestamp)));
		}
    }
	
	public static void testSS(){
		String amount = "金额：贰仟肆佰贰拾壹元玖角贰分  ￥2421.92元";
		amount = amount.replace(",", "");
//		String str = amount.substring(amount.indexOf("￥")+1, amount.length()-1);
//		amount.substring(amount.indexOf("￥"), amount.indexOf("元")-1);
//		System.out.println(str);
//		Pattern AMOUNT_PATTERN = Pattern.compile("(0|[1-9]\\d{0,11})\\.(\\d\\d)");
		Pattern AMOUNT_PATTERN = Pattern.compile("(0|[1-9]\\d{0,11})\\.(\\d\\d)?(?=[元])");
		
		Matcher matcher = AMOUNT_PATTERN.matcher(amount);
		if(!matcher.find()){
			throw new IllegalArgumentException("金额有误");
		}else{
			System.out.println(matcher.group(0));
		}
	}
	
	
	public static void testStr(){
		String str = "贷方卡号:9558851001005924358 卡名称:福建漳州天天快递结算公司 汇划种类:汇兑 客户 附言:漳州4.14号账单 用途: ";
		Pattern EXPRESS_PATTERN = Pattern.compile("[\\s].*?(?=\\s)");
		Matcher matcher = EXPRESS_PATTERN.matcher(str);
		if(matcher.find()){
			System.out.println(matcher.group(0));
			String str1 = matcher.group(0);
			String ss = str1.replace("卡名称:","").trim();
			System.out.println(ss);
		}
	}
	
	/**
	 * double类型：相减
	 * @param d1
	 * @param d2
	 * @return
	 */
	public Double sub(Double d1, Double d2){
		BigDecimal b1 = new BigDecimal(d1.toString());
		BigDecimal b2 = new BigDecimal(d2.toString());
		return b1.subtract(b2).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}
	
	
	/**
	 * List深层复制
	 * 
	 * @param src
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <L> List<L> deepCopy(List<L> src) {
		List<L> dest = new ArrayList<L>();

		try {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(byteOut);
			out.writeObject(src);

			ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
			ObjectInputStream in = new ObjectInputStream(byteIn);
			dest = (List<L>) in.readObject();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dest;
	}
	
	public static void testTwoDateCompare() throws ParseException{
		DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String dt = "2016-04-13 12:23:11";
		Date dd1 = sf.parse(dt);
		Date dd2 = sf.parse(sf.format(now));
		System.out.println(dd1);
		System.out.println(dd2);
		System.out.println(dd1.compareTo(dd2));
	} 
	
	public static void testArrayToString(){
		List<String> dd = new ArrayList<String>();
		dd.add("10");
		dd.add("20");
		String str1 = String.join(",", dd);
		System.out.println(dd.toString());
		System.out.println(str1);
		System.out.println(Arrays.asList(str1.split(",")));
	}
	
	public static void testEnum(){
		if(PaymentType.CASH.description.equals("现金"))
			System.out.println("ture");
		System.out.println(PaymentType.MOBILEPOSPAY.description);
	}
	
	public static void testNum(){
		List<String> list = new ArrayList<String>();
		for (int i = 1; i <= 593; i++) {
			list.add(String.valueOf(i));
		}
		
		int nRequest = 0;
		int records = 100; 
		int total = list.size();
		int average = total / records;
		int remnant = total % records;
		if(remnant == 0){
			nRequest = average;
		}else{
			nRequest = average + 1;
		}
		
		int fromIndex = 0;
		int toIndex = 0;
		for (int i = 0; i < nRequest; i++) {
			fromIndex = records * i;
			if(i == nRequest - 1 && remnant > 0){
				toIndex = records * i + remnant;
			}else{
				toIndex = records * (i + 1);
			}
			List<String> subList = list.subList(fromIndex, toIndex);
			System.out.println(subList.size()+","+subList.get(0)+","+subList.get(subList.size()-1));
		}
	}
	
	public static void testZeroNegate(){
		BigDecimal a = new BigDecimal(12.88).setScale(2, RoundingMode.HALF_EVEN);
		System.out.println(a.negate());
		
		BigDecimal b = new BigDecimal(-1.0);
		BigDecimal c = new BigDecimal(-1.0);
		System.out.println(b.equals(c));
	}
	
	/**
	 * 字符串分割
	 * @param str
	 */
	public static void strSplit(){
		String str = "SD-B001|SD-A001|LJ-A001";
		String[] s = str.split("\\|");
		for(int i=0; i<s.length; i++){
			System.out.println(s[i]);
		}
	}
	
	/**
	 * 类名小测试
	 * @throws ClassNotFoundException
	 */
	public static void classTest(){
		Date d = new Date();
		Class<? extends Date> cl = d.getClass();
		String name = cl.getName();
		System.out.println(name);
		try {
			Class<?> cl2 = Class.forName("java.lang.Double");
			Object dd = cl2.newInstance();
			System.out.println(dd);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) {	
		myG.setColor(Color.red);
		myG.fillRect(20, 40, 60, 80);
		font =new Font("FENGTAO",Font.BOLD,15);
		myG.setFont(font);
		myG.setColor(Color.blue);
		
	}
	public static void getTime(){
		long now = System.currentTimeMillis();
		long year = now/1000/60/60/24/365+1970;
		System.out.println(now);
		System.out.println(year);
	}
	
	public static void testFor1(){
		int count = 0; 
		for(int i=1; i<4; i++){
			System.out.println(i);
			if(count == 1){
				continue;
			}
			count++;
		}
	}
	
	/**
	 * 内循环跳出小测试
	 */
	public static void testFor2(){
		a:
			for(int i=1; i<5; i++){
				for(int j=1; j<5; j++){
					System.out.println(j);
					if(i==3&&j==2) break a;
				}
			}
	}
	
	public static boolean getEnv(){
		return System.getenv().containsKey("aa");
	}
	
	public static void dateTest(){
		
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(new SimpleDateFormat("yyyyMMdd").parse("20151201"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println(calendar.getTime());
	}
	
	public static void dateTest2(){
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.DAY_OF_MONTH, -1);
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		System.out.println(df.format(calender.getTime()));
	}
	
	public static long getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }
	
}
