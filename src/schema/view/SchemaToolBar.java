package schema.view;

import javax.swing.JToolBar;

import app.controller.actions.SchemaAction;
import schema.controller.actions.DeleteSchemaAction;
import schema.controller.actions.NewDBSchemaAction;
import schema.controller.actions.NewJsonSchemaAction;
import schema.controller.actions.OpenSchemaAction;
import schema.controller.actions.SaveAsSchemaAction;
import schema.controller.actions.ValidateSchemaAction;

public class SchemaToolBar extends JToolBar {
	
	
	public SchemaToolBar(){
		super();
		this.setFloatable(true);
		this.add(new NewJsonSchemaAction());
		this.add(new NewDBSchemaAction());
		this.add(new OpenSchemaAction());
		this.add(new SaveAsSchemaAction());
		this.add(new DeleteSchemaAction());
		this.addSeparator();
		this.add(new ValidateSchemaAction());
	}
}
