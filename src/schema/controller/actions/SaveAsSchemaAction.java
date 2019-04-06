package schema.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.apache.commons.io.FilenameUtils;

import app.Singleton;
import schema.view.SchemaDialog;

public class SaveAsSchemaAction extends AbstractAction{
	
	public SaveAsSchemaAction(){
		putValue(NAME, Singleton.getInstance().getResourceBundle().getString("save as"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl A"));
		putValue(SMALL_ICON, new ImageIcon("images/saveAs_icon.png"));
		putValue(LARGE_ICON_KEY, new ImageIcon("images/saveAs_icon_toolbar.png"));
		putValue(SHORT_DESCRIPTION, Singleton.getInstance().getResourceBundle().getString("save as"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String text;
		
		SchemaDialog sd = (SchemaDialog) javax.swing.FocusManager.getCurrentManager().getActiveWindow();
		text=sd.getTp().getText();
		
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int userSelection = fileChooser.showSaveDialog(null);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    if (FilenameUtils.getExtension(fileToSave.getName()).equalsIgnoreCase("json")) {
		        //nista
		    } else {
		        fileToSave = new File(fileToSave.toString() + ".json");  // dodaj .json
		        
		    }
		    try {
				Singleton.getInstance().getSchemaDialog().getHandler().save(text,fileToSave);
			} catch (IOException e) {
				sd.getTp().setText(text);
			}
		}
	}
	
}
