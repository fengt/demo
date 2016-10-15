package org.core1.script;

import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScriptTest {
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				String language;
				if(args.length == 0) language = "js";
				else language = args[0];
				
				ScriptEngineManager manager = new ScriptEngineManager();
				System.out.println("Available factories: ");
				for(ScriptEngineFactory factory : manager.getEngineFactories()){
					System.out.println(factory.getEngineName());
				}
				
				final ScriptEngine engine = manager.getEngineByName(language);
				
				if(engine == null){
					System.err.println("No engine for " + language);
					System.exit(1);
				}
				
				ButtonFrame frame = new ButtonFrame();
				
				try {
					File initFile = new File("init." + language);
					if(initFile.exists()){
						engine.eval(new FileReader(initFile));
					}
					
					getComponentBindings(frame, engine);
					
					final Properties events = new Properties();
					events.load(new FileReader(language + ".properties"));
					for(final Object e : events.keySet()){
						String[] s = ((String)e).split("\\.");
						addListener(s[0], s[1], (String)events.get(e), engine);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("ScriptTest");
				frame.setVisible(true);
			}
		});
	}
	
	private static void getComponentBindings(Component c, ScriptEngine engine){
		String name = c.getName();
		if(name != null) engine.put(name, c);
		if(c instanceof Container){
			for(Component child : ((Container)c).getComponents()){
				getComponentBindings(child, engine);
			}
		}
	}
	
	private static void addListener(String beanName, String eventName, final String scriptCode,
			final ScriptEngine engine) throws IllegalArgumentException, IntrospectionException,
			IllegalAccessException, InvocationTargetException{
		Object bean = engine.get(beanName);
		EventSetDescriptor descriptor = getEventSetDescriptor(bean, eventName);
		if(descriptor == null) return;
		descriptor.getAddListenerMethod().invoke(bean,
				Proxy.newProxyInstance(null, 
						new Class[]{descriptor.getListenerType()}, 
						new InvocationHandler(){
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
						engine.eval(scriptCode);
						return null;
					}
				}));
	}
	
	private static EventSetDescriptor getEventSetDescriptor(Object bean, String eventName) throws IntrospectionException{
		for(EventSetDescriptor descriptor : Introspector.getBeanInfo(bean.getClass()).getEventSetDescriptors())
			if(descriptor.getName().equals(eventName)) return descriptor;
		return null;
	}
}


class ButtonFrame extends JFrame{
	public ButtonFrame(){
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		panel = new JPanel();
		panel.setName("panel");
		add(panel);
		
		yellowButton = new JButton("Yellow");
		yellowButton.setName("yellowButton");
		buleButton = new JButton("Bule");
		buleButton.setName("buleButton");
		redButton = new JButton("Red");
		redButton.setName("redButton");
		
		panel.add(yellowButton);
		panel.add(buleButton);
		panel.add(redButton);
	}
	
	
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
	
	private JPanel panel;
	private JButton yellowButton;
	private JButton buleButton;
	private JButton redButton;
}













