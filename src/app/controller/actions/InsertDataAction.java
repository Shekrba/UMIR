package app.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import app.view.MyJTable;
import javax.swing.KeyStroke;

import app.Singleton;
import app.model.Table;
import app.view.MyTableModel;
import app.view.TableModelGetter;

public class InsertDataAction extends AbstractAction{

	public InsertDataAction() {
		
		putValue(NAME, Singleton.getInstance().getResourceBundle().getString("connect database"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_I);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift I"));
		putValue(SMALL_ICON, new ImageIcon("images/data.png"));
		putValue(LARGE_ICON_KEY, new ImageIcon("images/dataL.png"));
		putValue(SHORT_DESCRIPTION, Singleton.getInstance().getResourceBundle().getString("connect database"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
	
		Singleton.getInstance().getDbHandler().insertData();
	}
}
