package org.core1.db;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class ViewDBFrame extends JFrame {
	public ViewDBFrame(){
		setTitle("ViewDB");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		tableNames = new JComboBox<String>();
		tableNames.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				showTable((String)tableNames.getSelectedItem());
			}
		});
		add(tableNames, BorderLayout.NORTH);
		
		try {
			readDatabaseProperties();
			Connection conn = getConnection();
			try {
				DatabaseMetaData meta = conn.getMetaData();
				ResultSet mrs = meta.getTables(null, null, null, new String[]{"TABLE"});
				while(mrs.next())
					tableNames.addItem(mrs.getString(3));
			} finally{
				conn.close();
			}
		} catch(SQLException | IOException e){
			JOptionPane.showMessageDialog(this, e);
		}
		
		JPanel buttonPanel = new JPanel();
		
		add(buttonPanel, BorderLayout.SOUTH);
		previousButton = new JButton("Previous");
		previousButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				showPreviousRow();
			}
		});
		buttonPanel.add(previousButton);
		
		nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				showNextRow();
			}
		});
		buttonPanel.add(nextButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				deleteRow();
			}
		});
		buttonPanel.add(deleteButton);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				saveChanges();
			}
		});
		buttonPanel.add(saveButton);
	} 
	
	public void showTable(String tableName){
		try {
			Connection conn = getConnection();
			try{
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery("SELECT * FROM " + tableName);
				//copy into cached row set	
//				crs = new CachedRowSetImpl();
				crs.setTableName(tableName);
				crs.populate(rs);
			} finally{
				conn.close();
			}
			
			if(scrollPane != null) remove(scrollPane);
			dataPanel = new DataPanel(crs);
			scrollPane = new JScrollPane(dataPanel);
			add(scrollPane, BorderLayout.CENTER);
			validate();
			showNextRow();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	public void showPreviousRow(){
		try {
			if(crs == null || crs.isFirst()) return;
			crs.previous();
			dataPanel.showRow(crs);
		} catch (SQLException e) {
			for(Throwable t: e)
				t.printStackTrace();
		}
	}
	
	public void showNextRow(){
		try {
			if(crs == null || crs.isLast()) return;
			crs.next();
			dataPanel.showRow(crs);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	public void deleteRow(){
		try {
			Connection conn = getConnection();
			try{
				crs.deleteRow();
				crs.acceptChanges(conn);
				if(!crs.isLast()) crs.next();
				else if(!crs.isFirst()) crs.previous();
				else crs = null;
				dataPanel.showRow(crs);
			} finally{
				conn.close();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	public void saveChanges(){
		try {
			Connection conn = getConnection();
			try{
				dataPanel.setRow(crs);
				crs.acceptChanges(conn);
			} finally{
				conn.close();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	private void readDatabaseProperties() throws IOException{
		props = new Properties();
		FileInputStream in = new FileInputStream("/Users/ftag/Documents/java/studyspace/javaCore/src/cn/halcyon/db/db.properties");
		props.load(in);
		in.close();
		String drivers = props.getProperty("driverClassName");
		if(drivers != null){
//			System.setProperty("driverClassName", drivers);//not work.
			try {
				Class.forName(drivers);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} 
	}
	
	private Connection getConnection() throws SQLException{
		String url = props.getProperty("url");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		
		return DriverManager.getConnection(url, username, password);
	}
	
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 200;
	
	private static final long serialVersionUID = 123321456456L;
	
	private JButton previousButton;
	private JButton nextButton;
	private JButton deleteButton;
	private JButton saveButton;
	private DataPanel dataPanel;
	private Component scrollPane;
	private JComboBox<String> tableNames;
	private Properties props;
	private CachedRowSet crs;
}
