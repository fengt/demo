package org.core1.inherit;

import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionTest {
	
	public static void main(String[] args){
		String name;
		if(args.length > 0) name = args[0];
		else{
			Scanner in = new Scanner(System.in);
			System.out.println("Enter class name (e.g. java.util.date): ");
			name = in.next();
		}
		
		try {
			Class cl = Class.forName(name);
			Class supercl = cl.getSuperclass();
			String modifiers = Modifier.toString(cl.getModifiers());
			if(modifiers.length() > 0) System.out.println(modifiers + " ");
			System.out.print("class " + name);
			if(supercl != null && supercl != Object.class) System.out.println(" extends " 
					+ supercl.getName());
			
			System.out.print("\n{\n");
			//printConstructors(cl);
			//printMethods(cl);
			//printFields(cl);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	
}
