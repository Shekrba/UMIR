package app.view;

import javax.swing.JToolBar;

import app.controller.actions.DownHierarchyAction;
import app.controller.actions.SchemaAction;
import app.controller.actions.UpHierarchyAction;
import app.controller.crud.CreateRecordAction;
import app.controller.crud.DeleteRecordAction;
import app.controller.crud.ReadRecordAction;
import app.controller.crud.UpdateRecordAction;

public class TableToolBar extends JToolBar {
	
	
	public TableToolBar(){
		super();
		this.setFloatable(true);
		this.add(new UpHierarchyAction());
		this.add(new DownHierarchyAction());
		this.addSeparator();
		this.add(new CreateRecordAction());
		this.add(new DeleteRecordAction());
		this.add(new UpdateRecordAction());
		this.add(new ReadRecordAction());
		this.setFloatable(false);
	}
}
