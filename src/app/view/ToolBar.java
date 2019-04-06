package app.view;

import javax.swing.JToolBar;

import app.controller.actions.DownHierarchyAction;
import app.controller.actions.InsertDataAction;
import app.controller.actions.SchemaAction;
import app.controller.actions.UpHierarchyAction;
import app.controller.crud.CreateRecordAction;
import app.controller.crud.DeleteRecordAction;
import app.controller.crud.ReadRecordAction;
import app.controller.crud.UpdateRecordAction;



public class ToolBar extends JToolBar {
	
	
	public ToolBar(){
		super();
		this.setFloatable(true);
		this.add(new SchemaAction());
		this.add(new InsertDataAction());
	}
}
