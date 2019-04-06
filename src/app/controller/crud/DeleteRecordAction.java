package app.controller.crud;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import app.Singleton;
import app.model.Table;
import app.view.MyJTable;
import app.view.MyTableModel;
import app.view.TableModelGetter;

public class DeleteRecordAction extends AbstractAction{

	public DeleteRecordAction(){
		putValue(NAME, Singleton.getInstance().getResourceBundle().getString("delete row"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_D);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl D"));
		putValue(SMALL_ICON, new ImageIcon("images/delete.png"));
		putValue(LARGE_ICON_KEY, new ImageIcon("images/deleteL.png"));
		putValue(SHORT_DESCRIPTION, Singleton.getInstance().getResourceBundle().getString("delete row"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int res=JOptionPane.showConfirmDialog(null,Singleton.getInstance().getResourceBundle().getString("are you sure you want to delete selected row"),Singleton.getInstance().getResourceBundle().getString("confirm"),JOptionPane.YES_NO_OPTION);
		if(res == JOptionPane.YES_OPTION){
			
			Table table = Singleton.getInstance().getMainFrame().getShowingParrent();
			
			MyTableModel tableModel = TableModelGetter.getTableModel(table);
			
			MyJTable jtable = Singleton.getInstance().getMainFrame().getParrentTable();
			
			Object objects [] = new Object [table.getPrimaryKey().size()];
			
			int i = 0;
			
			for(String s : table.getPrimaryKey()){
				String name = table.getAttributes().get(s).getName();
				objects[i] = tableModel.getValueAt(jtable.getSelectedRow(), tableModel.getIndexByName(name));
				i++;
			}
			
			Singleton.getInstance().getDbHandler().deleteData(objects);
		}
	}

	
}
