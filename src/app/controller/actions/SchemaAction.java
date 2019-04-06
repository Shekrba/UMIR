package app.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import app.Singleton;
import schema.abstractFactory.FactoryMaker;
import schema.view.SchemaDialog;

public class SchemaAction extends AbstractAction{

	 public SchemaAction(){
			putValue(NAME, Singleton.getInstance().getResourceBundle().getString("schema"));
			putValue(MNEMONIC_KEY, KeyEvent.VK_S);
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
			putValue(SMALL_ICON, new ImageIcon("images/schema_icon.png"));
			putValue(LARGE_ICON_KEY, new ImageIcon("images/schema_icon_toolbar.png"));
			putValue(SHORT_DESCRIPTION, Singleton.getInstance().getResourceBundle().getString("schema"));
		}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(Singleton.getInstance().getSchemaDialog()==null) {
			SchemaDialog sd=new SchemaDialog();
			Singleton.getInstance().setSchemaDialog(sd);
			sd.initDialog(FactoryMaker.getFactory("JSON"));
		}else {
			Singleton.getInstance().getSchemaDialog().setVisible(true);
		}
	}

}
