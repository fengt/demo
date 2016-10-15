package org.core1.db;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.RowSet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DataPanel extends JPanel {

	public DataPanel(RowSet rs) throws SQLException{
		fields = new ArrayList<JTextField>();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		
		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			gbc.gridy = i - 1;
			String columnName = rsmd.getColumnLabel(i);
			gbc.gridx = 0;
			gbc.anchor = GridBagConstraints.EAST;
			add(new JLabel(columnName), gbc);
			
			int columnWidth = rsmd.getColumnDisplaySize(i);
			JTextField tb = new JTextField(columnWidth);
			if(!rsmd.getColumnClassName(i).equals("java.lang.String"))
				tb.setEditable(false);
			
			fields.add(tb);
			gbc.gridx = 1;
			gbc.anchor = GridBagConstraints.WEST;
			add(tb, gbc);
		}
	}
	
	public void showRow(ResultSet rs) throws SQLException{
		for (int i = 1; i < fields.size(); i++) {
			String field = rs.getString(i);
			JTextField tb = (JTextField)fields.get(i - 1);
			tb.setText(field);
		}
	}
	
	public void setRow(RowSet rs) throws SQLException{
		for (int i = 1; i < fields.size(); i++) {
			String field = rs.getString(i);
			JTextField tb = (JTextField)fields.get(i - 1);
			if(!field.equals(tb.getText()))
				rs.updateString(i, tb.getText());
		}
		rs.updateRow();
	}
	
	private static final long serialVersionUID = 123321456457L;
	private ArrayList<JTextField> fields;
}
