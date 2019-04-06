package schema.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import app.Singleton;
import schema.abstractFactory.AbstractFactory;
import schema.abstractFactory.FactoryMaker;

public class NewDBSchemaAction extends AbstractAction{

	 public NewDBSchemaAction(){
			putValue(NAME, Singleton.getInstance().getResourceBundle().getString("new db shcema"));
			putValue(MNEMONIC_KEY, KeyEvent.VK_B);
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl B"));
			putValue(SMALL_ICON, new ImageIcon("images/db.png"));
			putValue(LARGE_ICON_KEY, new ImageIcon("images/dbL.png"));
			putValue(SHORT_DESCRIPTION, Singleton.getInstance().getResourceBundle().getString("new db shcema"));
		}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		AbstractFactory factory=FactoryMaker.getFactory("DB");
		Singleton.getInstance().getSchemaDialog().setParams(factory);
	}

}