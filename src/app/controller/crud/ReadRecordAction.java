package app.controller.crud;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import app.Singleton;
import app.model.attribute.Attribute;
import app.view.crud.CreateDialog;
import app.view.crud.ReadDialog;

public class ReadRecordAction extends AbstractAction{

	public ReadRecordAction(){
		putValue(NAME, Singleton.getInstance().getResourceBundle().getString("search"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_F);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl F"));
		putValue(SMALL_ICON, new ImageIcon("images/read.png"));
		putValue(LARGE_ICON_KEY, new ImageIcon("images/readL.png"));
		putValue(SHORT_DESCRIPTION, Singleton.getInstance().getResourceBundle().getString("search"));
	}

	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ReadDialog rd=new ReadDialog();		
	}

	
	
}
