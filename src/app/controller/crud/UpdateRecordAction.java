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
import app.view.crud.UpdateDialog;

public class UpdateRecordAction extends AbstractAction{

	public UpdateRecordAction(){
		putValue(NAME, Singleton.getInstance().getResourceBundle().getString("update row"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_U);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl U"));
		putValue(SMALL_ICON, new ImageIcon("images/update.png"));
		putValue(LARGE_ICON_KEY, new ImageIcon("images/updateL.png"));
		putValue(SHORT_DESCRIPTION, Singleton.getInstance().getResourceBundle().getString("update row"));
	}

	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		UpdateDialog ud=new UpdateDialog();		
	}
	
}
