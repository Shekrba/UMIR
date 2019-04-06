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

public class CreateRecordAction extends AbstractAction{

	public CreateRecordAction(){
		putValue(NAME, Singleton.getInstance().getResourceBundle().getString("add row"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_N);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
		putValue(SMALL_ICON, new ImageIcon("images/create.png"));
		putValue(LARGE_ICON_KEY, new ImageIcon("images/createL.png"));
		putValue(SHORT_DESCRIPTION, Singleton.getInstance().getResourceBundle().getString("add row"));
	}

	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		CreateDialog cd=new CreateDialog(new ArrayList<Attribute>(Singleton.getInstance().getMainFrame().getShowingParrent().getAttributes().values()));		
	}

	
	
}
