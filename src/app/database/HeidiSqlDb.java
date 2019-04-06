package app.database;

import java.nio.channels.ShutdownChannelGroupException;
import java.security.Signature;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import app.view.MyJTable;
import javax.swing.JTextField;

import app.Singleton;
import app.model.Table;
import app.model.attribute.Attribute;
import app.view.MyTableModel;
import app.view.TableModelGetter;
import app.view.boolComponent.CreateBoolComponent;
import app.view.charComponent.CreateCharComponent;
import app.view.dateComponent.CreateDateComponent;
import app.view.intComponent.CreateIntComponent;
import app.view.numberComponent.CreateNumberComponent;
import app.view.stringComponent.CreateStringComponent;

/**
 * @author Milan Skrbic
 * 
 * Klasa za hendlovanje CRUD operacija
 *
 */
public class HeidiSqlDb implements DBConnectionHandler {

	/*Povezuje aplikaciju sa bazom i pravi upit za selektovanje tabela radi ucitavanja u model aplikacije*/
	@Override
	public void insertData(){
		try {
			Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim8-2","psw-2018-tim8-2","tim8-213636262");
			
			HashMap<String, Table> allTables = Singleton.getInstance().getAllTables();
			
			for (Table t : allTables.values()) {
				String s = t.getCode();
				String sql = "select * from " + s;
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				ResultSet rset = pstmt.executeQuery();
				ResultSetMetaData meta = rset.getMetaData();
				
				MyTableModel tableModel = TableModelGetter.getTableModel(t);
				int rowCount = tableModel.getRowCount();
				for (int k = 0; k < rowCount; k++) {
					tableModel.removeRow(0);
				}
				
				int row = 0;
				
				while(rset.next()){		
					
					Object [] o = new Object[meta.getColumnCount()];
					
					
					for (int i = 1; i <= meta.getColumnCount(); i++) {
						
						DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
						try {
							Date date = formater.parse(rset.getString(i));
							
							
							String name = t.getAttributes().get(meta.getColumnName(i)).getName();
							o[tableModel.getIndexByName(name)] = date;
							
						} catch (Exception e2) {
							// TODO: handle exception
							String name = t.getAttributes().get(meta.getColumnName(i)).getName();
							if(meta.getColumnClassName(i).equals("java.lang.Boolean")) {
								o[tableModel.getIndexByName(name)] = rset.getBoolean(i);
							}else {
								o[tableModel.getIndexByName(name)] = rset.getObject(i);
							}
						}
					}
					tableModel.addRow(o);
					row++;
				}
				
				rset.close();
				pstmt.close();
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	/*Pravi upit kojim se dodaje red u tabelu u 
	 * bazi pa u tabelu u aplikaciji i hvata izuzetak ako ga ima zbog prikaza korisniku*/
	@Override
	public boolean addData(HashMap<String, JComponent> comps){
		try {
			Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim8-2","psw-2018-tim8-2","tim8-213636262");
			Table table = Singleton.getInstance().getMainFrame().getShowingParrent();
			
	//		HashMap<String, Table> allTables = Singleton.getInstance().getAllTables();
			
			String attrString = "(";
			String valueString = "(";
			Object row[] = new Object[table.getAttributes().size()];
			
			int i = 0;
			
			boolean comma = false;
			for (Attribute a : table.getAttributes().values()) {
				if(comma){
					attrString+= ", ";
					valueString+= ", ";
				}
				else{
					comma = true;
				}
					
				attrString+= a.getCode();
				
				JComponent c = comps.get(a.getCode());
				if(c instanceof JTextField){
					JTextField tf = (JTextField) c;
					
					if((tf.getText()).trim().equals("")){
						valueString += "null";
						row[i] = tf.getText();
					}
					else{
						switch(a.getType().getTypeName()){
						case "varchar":{
							valueString += "'"+ tf.getText() +"'";
							row[i] = tf.getText();
						}break;
						
						case "char":{
							valueString += "'"+ tf.getText() +"'";
							row[i] = tf.getText();
						}break;
						case "double":{
							valueString += tf.getText();
							row[i] = Double.parseDouble(tf.getText());
						}break;
						
						case "int":{
							valueString += tf.getText();
							row[i] = Integer.parseInt(tf.getText());
						}break;
						
						case "date":{
							DateFormat format1 = new SimpleDateFormat(Singleton.getInstance().getResourceBundle().getString("dateFormat"));
							DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
							
							Date date = format1.parse(tf.getText());
							String s = format2.format(date);
							
							valueString += "'"+s+"'";
							
							row[i] = date;
						}break;
						}
					}
				}else{
					JCheckBox cb = (JCheckBox) c;
					
					row[i] = cb.isSelected();
					
					if(cb.isSelected())
						valueString += "1";
					else
						valueString += "0";
				}
				i++;
			}
			attrString += ")";
			valueString += ")";
			
			
			String sql = "insert into "+ table.getCode() +" " + attrString + "values " + valueString;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.executeUpdate();
			
			pstmt.close();
					
			
			MyTableModel tableModel = TableModelGetter.getTableModel(table);
			tableModel.addRow(row);
			
			return true;
		} catch (Exception ex) {
			
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		}
	}
	
	/*Pravi upit kojim se anzurira red u tabeli u 
	 * bazi pa u tabeli u aplikaciji i hvata izuzetak ako ga ima zbog prikaza korisniku*/
	@Override
	public boolean updateData(HashMap<String, JComponent> comps){
		try {
			Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim8-2","psw-2018-tim8-2","tim8-213636262");
			Table table = Singleton.getInstance().getMainFrame().getShowingParrent();
			
	//		HashMap<String, Table> allTables = Singleton.getInstance().getAllTables();
			
			String attrString = "";
			String pkeys = "";
					
			
			boolean comma = false;
			
			for (Attribute a : table.getAttributes().values()) {
				
				if(!table.getPrimaryKey().contains(a.getCode())){
				
				if(comma){
					attrString+= ", ";
				}
				else{
					comma = true;
				}
					
				attrString+= a.getCode();
				
				attrString+= " = ";
							
				
				JComponent c = comps.get(a.getCode());
				if(c instanceof JTextField){
					JTextField tf = (JTextField) c;
					
					if((tf.getText()).trim().equals("")){
						attrString += "null";						
											
					}
					else{
						switch(a.getType().getTypeName()){
						case "varchar":{
							attrString += "'"+ tf.getText() +"'";
							
						}break;
						
						case "char":{
							attrString += "'"+ tf.getText() +"'";
							
						}break;
						case "double":{
							attrString += tf.getText();
							
						}break;
						
						case "int":{
							attrString += tf.getText();
							
						}break;
						
						case "date":{
							DateFormat format1 = new SimpleDateFormat(Singleton.getInstance().getResourceBundle().getString("dateFormat"));
							DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
							
							Date date = format1.parse(tf.getText());
							String s = format2.format(date);
							
							attrString += "'" +s+"'";
							
							
						}break;
						}
					}
				}else{
					JCheckBox cb = (JCheckBox) c;
					
					
					if(cb.isSelected()){
						attrString += "1";
						
					}
					else{
						attrString += "0";
						
					}
					
				}
			
				}
			}
					
			comma = false;
			
			for (Attribute a : table.getAttributes().values()) {
				if(table.getPrimaryKey().contains(a.getCode())){
					
					if(comma){
						pkeys+= " and ";
					}
					else{
						comma = true;
					}
					
					pkeys+= a.getCode() + " = ";
					
					JComponent c = comps.get(a.getCode());
					
					if(c instanceof JTextField){
					JTextField tf = (JTextField) c;
						switch(a.getType().getTypeName()){
						case "varchar":{
																
								pkeys += "'"+ tf.getText() +"'";
						}break;
						
						case "char":{
							
								pkeys += "'"+ tf.getText() +"'";
						}break;
						case "double":{
							
								pkeys += tf.getText();
						}break;
						
						case "int":{
							
								pkeys += tf.getText();
						}break;
						
						case "date":{
							DateFormat format1 = new SimpleDateFormat(Singleton.getInstance().getResourceBundle().getString("dateFormat"));
							DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
							
							Date date = format1.parse(tf.getText());
							String s = format2.format(date);
							
						
							
							
							pkeys += s;
						}
					}
					}else{
						JCheckBox cb = (JCheckBox) c;
						
						
						if(cb.isSelected()){
							pkeys += "1";
							
						}
						else{
							pkeys += "0";
							
						}
					}
				}
			}
				
			
			
			String sql = "UPDATE "+ table.getCode() +" SET " + attrString + " WHERE " + pkeys ;

			System.out.println(sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.executeUpdate();
			
			pstmt.close();
					
			
			Singleton.getInstance().getDbHandler().insertData();
			
			return true;
		} catch (Exception ex) {
			
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		}
	}
	
	/*Pravi upit kojim se brise red u tabelu u 
	 * bazi pa u tabelu u aplikaciji i hvata izuzetak ako ga ima zbog prikaza korisniku*/
	@Override
	public boolean deleteData(Object objects[]){
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim8-2","psw-2018-tim8-2","tim8-213636262");
			
			Table table = Singleton.getInstance().getMainFrame().getShowingParrent();
			
			String pkeys = "";
			
			boolean comma = false;
			
			int i = 0;
			
			for(Attribute a : table.getAttributes().values()){
				if(table.getPrimaryKey().contains(a.getCode())){
					
					if(comma){
						pkeys+= " and ";
					}
					else{
						comma = true;
					}
					
					switch(a.getType().getTypeName()){
					case "varchar":{
															
						pkeys += a.getCode() + " = " + "'" +objects[i] + "'";
					}break;
					
					case "char":{
						
						pkeys += a.getCode() + " = " + "'" +objects[i] + "'";
					}break;
					case "double":{
						
						pkeys += a.getCode() + " = " + objects[i];
					}break;
					
					case "int":{
						
						pkeys += a.getCode() + " = " + objects[i];
					}break;
					
					case "date":{
			
						DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
					
						String s = format2.format(objects[i]);
						
						pkeys += a.getCode() + " = " + s;
					}break;
						
					case "boolean":{
						int x;
						if((Boolean)objects[i])
							x = 1;
						else
							x = 0;
						
						pkeys += a.getCode() + " = " + x;
					}break;
					}
					i++;
				}
			}
			String sql = "DELETE FROM "+ table.getCode() + " WHERE " + pkeys ;

			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.executeUpdate();
			
			pstmt.close();
					
			
			Singleton.getInstance().getDbHandler().insertData();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
}
