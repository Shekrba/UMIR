package schema.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import app.Singleton;
import schema.abstractFactory.FactoryMaker;

public class OpenSchemaAction extends AbstractAction{
	 
	public OpenSchemaAction(){
			putValue(NAME, Singleton.getInstance().getResourceBundle().getString("open schema"));
			putValue(MNEMONIC_KEY, KeyEvent.VK_O);
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl O"));
			putValue(SMALL_ICON, new ImageIcon("images/open_icon.png"));
			putValue(LARGE_ICON_KEY, new ImageIcon("images/open_icon_toolbar.png"));
			putValue(SHORT_DESCRIPTION, Singleton.getInstance().getResourceBundle().getString("open schema"));
		}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            String filename = fileChooser.getSelectedFile().getPath();
            String extension=FilenameUtils.getExtension(filename);
            if(extension.equals("json")){
            	Singleton.getInstance().getSchemaDialog().setParams(FactoryMaker.getFactory("JSON"));
            }else{
            	Singleton.getInstance().getSchemaDialog().setParams(FactoryMaker.getFactory("DB"));
            }
            try {
				Singleton.getInstance().getSchemaDialog().getHandler().load(new File(filename));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"Fajl nije u ispravnom formatu", "Error", JOptionPane.ERROR_MESSAGE);
			}
            
        }
        else if (result == JFileChooser.CANCEL_OPTION)
        {   
           
        }
        else if (result == JFileChooser.ERROR_OPTION)
        {
          
        }                             
	}

}
