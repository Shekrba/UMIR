package schema.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import org.json.JSONObject;

import app.Singleton;
import schema.abstractFactory.AbstractFactory;
import schema.abstractFactory.FactoryMaker;
import schema.view.SchemaDialog;

public class NewJsonSchemaAction extends AbstractAction{

	 public NewJsonSchemaAction(){
			putValue(NAME, Singleton.getInstance().getResourceBundle().getString("new json schema"));
			putValue(MNEMONIC_KEY, KeyEvent.VK_N);
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl J"));
			putValue(SMALL_ICON, new ImageIcon("images/new_schema_icon.png"));
			putValue(LARGE_ICON_KEY, new ImageIcon("images/new_schema_icon_toolbar.png"));
			putValue(SHORT_DESCRIPTION, Singleton.getInstance().getResourceBundle().getString("new json schema"));
		}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		AbstractFactory factory=FactoryMaker.getFactory("JSON");
		Singleton.getInstance().getSchemaDialog().setParams(factory);
	}

}
