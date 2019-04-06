package app.view;

import java.awt.Component;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import app.Singleton;



/**
 * @author Milan Skrbic
 *
 *Klasa nasledjuje JTable i sluzi radi definisanja TableCellRenderera
 */
public class MyJTable extends JTable {

	public MyJTable(MyTableModel tm) {
		// TODO Auto-generated constructor stub
		super(tm);
		
		
		
	
		
	}
	
	/*Poziva konstruktor MyJTable klase da bi mu namestio TableCellRenderer zbog oblika datuma u tabeli*/
	public static MyJTable create(MyTableModel tm) {
        MyJTable a = new MyJTable(tm);
        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {

		    SimpleDateFormat f = new SimpleDateFormat(Singleton.getInstance().getResourceBundle().getString("dateFormat"));

		    public Component getTableCellRendererComponent(JTable table,

		            Object value, boolean isSelected, boolean hasFocus,

		            int row, int column) {

		        if( value instanceof Date) {

		            value = f.format(value);

		        }

		        return super.getTableCellRendererComponent(table, value, isSelected,

		                hasFocus, row, column);

		    }

		};
	
		for(int i = 0; i < tm.getColumnCount(); i++){				
			
			if(tm.getColumnClass(i).equals(Date.class))
				a.getColumnModel().getColumn(i).setCellRenderer(tableCellRenderer);		

		}
        return a;
    }
	
	/*Postavlja TableCellRenderer*/
	@Override
	public void setModel(TableModel tm) {
		// TODO Auto-generated method stub
		super.setModel(tm);
		 DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {

			    SimpleDateFormat f = new SimpleDateFormat(Singleton.getInstance().getResourceBundle().getString("dateFormat"));

			    public Component getTableCellRendererComponent(JTable table,

			            Object value, boolean isSelected, boolean hasFocus,

			            int row, int column) {

			        if( value instanceof Date) {

			            value = f.format(value);

			        }

			        return super.getTableCellRendererComponent(table, value, isSelected,

			                hasFocus, row, column);

			    }

			};
			
			for(int i = 0; i < tm.getColumnCount(); i++){				
				if(tm.getColumnClass(i).equals(Date.class))
					this.getColumnModel().getColumn(i).setCellRenderer(tableCellRenderer);		

			}
	}
}
