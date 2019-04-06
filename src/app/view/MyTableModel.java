package app.view;


import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {
	
	private ArrayList<String> columnNames=new ArrayList<String>();
	private int boolTypes[]=new int[10];
	private int i=0;
	private ArrayList<Integer> dateTypes=new ArrayList<Integer>();
	
	public ArrayList<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(ArrayList<String> columnNames) {
		this.columnNames = columnNames;
	}

	public ArrayList<Integer> getDateTypes() {
		return dateTypes;
	}

	public void setDateTypes(ArrayList<Integer> dateTypes) {
		this.dateTypes = dateTypes;
	}

	public void addColumName(String s){
		columnNames.add(s);
	}
	
	public void addDate(Integer x) {
		dateTypes.add(x);
	}
	
	public void addBool(int x) {
		boolTypes[i]=x;
		i++;
	}
	
	
	
	public ArrayList<Object> getValueAtRow(int rowIndex) {
		
		ArrayList<Object> list = new ArrayList<Object>();
		
		for(int i = 0; i < this.getColumnCount(); i++){
			list.add(this.getValueAt(rowIndex, i));
		}
		
		return list;
	}
	
	public int getIndexByName(String name){
		
		for(int i = 0; i < this.getColumnCount(); i++){
			if(this.getColumnName(i).equals(name))
				return i;
		}
		return -1;
	}
	
	    @Override
	    public boolean isCellEditable(int rowIndex, int colIndex) {
	        return false;
	    }    
	
	    @Override
	    public Class getColumnClass(int col) {
	        for(int x=0; x<i;x++) {
	        	if(boolTypes[x]==col)
	        		return Boolean.class;
	        }
	        if(dateTypes.contains(col))
	        	return Date.class;
	        return Object.class;
	    }
	    
	    @Override
	    public String getColumnName(int column) {
	    	// TODO Auto-generated method stub
	    	return columnNames.get(column);
	    }
}
