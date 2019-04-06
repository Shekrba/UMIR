package schema.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import app.Singleton;

public class DeleteSchemaAction extends AbstractAction{

	public DeleteSchemaAction(){
		putValue(NAME, Singleton.getInstance().getResourceBundle().getString("delete schema"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_R);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl R"));
		putValue(SMALL_ICON, new ImageIcon("images/delete_icon.png"));
		putValue(LARGE_ICON_KEY, new ImageIcon("images/delete_icon_toolbar.png"));
		putValue(SHORT_DESCRIPTION, Singleton.getInstance().getResourceBundle().getString("delete schema"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int res=JOptionPane.showConfirmDialog(null,Singleton.getInstance().getResourceBundle().getString("are you sure you want to delete active schema"),Singleton.getInstance().getResourceBundle().getString("confirm"),JOptionPane.YES_NO_OPTION);
		if(res==JOptionPane.YES_OPTION) {
			Singleton.getInstance().getSchemaDialog().setHandler(null);
			Singleton.getInstance().getSchemaDialog().setValidator(null);
			Singleton.getInstance().getSchemaDialog().setParser(null);
		}
	}

	
}
