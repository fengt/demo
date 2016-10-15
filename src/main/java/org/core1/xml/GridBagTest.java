package org.core1.xml;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class GridBagTest {
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
//				String filename = args.length == 0 ? "E:/document/sublime_project/java/fontdialog.xml" : args[0];
//				String filename = "";
				JFrame frame = new FontFrame("E:/document/sublime_project/java/fontdialog.xml");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
}


class FontFrame extends JFrame{
	public FontFrame(String filename){
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("GridBagTest");
		
		gridbag = new GridBagPane(filename);
		add(gridbag);
		
		face = (JComboBox)gridbag.get("face");
		size = (JComboBox)gridbag.get("size");
		bold = (JCheckBox)gridbag.get("bold");
		italic = (JCheckBox)gridbag.get("italic");
		
		face.setModel(new DefaultComboBoxModel(new Object[]{"Serif", "SansSerif", "Monospaced", "Dialog", "DialogInput"}));
		size.setModel(new DefaultComboBoxModel(new Object[]{"8", "10", "12", "15", "18", "24", "36", "48"}));
		
		ActionListener listener = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setSample();
			}
		};
		
		face.addActionListener(listener);
		size.addActionListener(listener);
		bold.addActionListener(listener);
		italic.addActionListener(listener);
		setSample();
	}
	
	public void setSample(){
		String fontFace = (String)face.getSelectedItem();
		int fontSize = Integer.parseInt((String)size.getSelectedItem());
		JTextArea sample = (JTextArea)gridbag.get("sample");
		int fontStyle = (bold.isSelected() ? Font.BOLD : 0) + (bold.isSelected() ? Font.ITALIC : 0);
		
		sample.setFont(new Font(fontFace, fontStyle, fontSize));
		sample.repaint();
	}
	
	private GridBagPane gridbag;
	private JComboBox face;
	private JComboBox size;
	private JCheckBox bold;
	private JCheckBox italic;
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 400;
}
