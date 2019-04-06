package schema.controller.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import org.everit.json.schema.ValidationException;
import org.json.JSONException;
import org.json.JSONObject;

import app.Singleton;
import schema.view.SchemaDialog;

public class ValidateSchemaAction extends AbstractAction{
	
	public ValidateSchemaAction(){
		putValue(NAME, Singleton.getInstance().getResourceBundle().getString("validation"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_V);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl V"));
		putValue(SMALL_ICON, new ImageIcon("images/validate_icon.png"));
		putValue(LARGE_ICON_KEY, new ImageIcon("images/validate_icon_toolbar.png"));
		putValue(SHORT_DESCRIPTION, Singleton.getInstance().getResourceBundle().getString("validation"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(Singleton.getInstance().getSchemaDialog().getValidator().validate(Singleton.getInstance().getSchemaDialog().getHandler().getText())!=null) {
			JOptionPane.showMessageDialog(null,"Validation successful", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
		
	 
	}
	
}
