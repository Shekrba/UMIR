package app.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import app.Singleton;
import app.controller.actions.DownHierarchyAction;
import app.controller.actions.InsertDataAction;
import app.controller.actions.SchemaAction;
import app.controller.actions.UpHierarchyAction;



public class MenuBar extends JMenuBar{
	
	public MenuBar(){
		super();
		JMenu mFile=new JMenu(Singleton.getInstance().getResourceBundle().getString("file"));
		add(mFile);
		mFile.add(new SchemaAction());
		mFile.add(new InsertDataAction());
		
		
		
		JMenu mLang=new JMenu(Singleton.getInstance().getResourceBundle().getString("language"));
		
		
		add(mLang);
	
		JCheckBoxMenuItem mniSrpski=new JCheckBoxMenuItem("Srb");
		
		
		mniSrpski.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("sr","RS"));
				Singleton.getInstance().changeLanguage();
				
				
			}
		});
		mLang.add(mniSrpski);
		
		
		
		
		
		JCheckBoxMenuItem mniEngleski=new JCheckBoxMenuItem("Eng");
		mniEngleski.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("en","US"));
				Singleton.getInstance().changeLanguage();
			}
		});
		mLang.add(mniEngleski);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(mniSrpski);
		bg.add(mniEngleski);
		
		if(Singleton.getInstance().getResourceBundle().getLocale().getDefault().getLanguage().toString().equals("sr"))
			mniSrpski.setSelected(true);
		else
			mniEngleski.setSelected(true);
		
		
				
		
	}
	
}


