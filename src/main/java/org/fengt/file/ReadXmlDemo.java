//package org.fengt.file;
//
//import java.io.File;
//import java.util.Iterator;
//import java.util.List;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//
//public class ReadXmlDemo {
//	
//	public static void main(String[] args){
//		
//	}
//	
//	public static void readStoreIn(String filename){
//		SAXReader reader = new SAXReader();
//		File file = new File(filename);
//		try {
//			Document doc = reader.read(file);
//			Element rootElmt= doc.getRootElement();
//			List list = rootElmt.elements("A");
//			parseNovel(list);
//			
//			List list1 = rootElmt.elements("B");
//			
//			List list2 = rootElmt.elements("C");
//			
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void parseNovel(List list){
//		Iterator it = list.iterator();
//		while(it.hasNext()){
//			/*A中所有的元素*/
//			Element novelElmt = (Element)it.next();
//			
//			System.out.println(novelElmt.elementText("a"));
//			
//		}
//	}
//}
