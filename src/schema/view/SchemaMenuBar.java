package schema.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import app.Singleton;
import app.controller.actions.SchemaAction;
import schema.controller.actions.DeleteSchemaAction;
import schema.controller.actions.NewDBSchemaAction;
import schema.controller.actions.NewJsonSchemaAction;
import schema.controller.actions.OpenSchemaAction;
import schema.controller.actions.SaveAsSchemaAction;
import schema.controller.actions.ValidateSchemaAction;

public class SchemaMenuBar extends JMenuBar{
	
	public SchemaMenuBar(){
		super();
		JMenu mFile=new JMenu(Singleton.getInstance().getResourceBundle().getString("file"));
		add(mFile);
		mFile.add(new NewJsonSchemaAction());
		mFile.add(new NewDBSchemaAction());
		mFile.add(new OpenSchemaAction());
		mFile.add(new SaveAsSchemaAction());
		mFile.add(new DeleteSchemaAction());
		JMenu mValidate=new JMenu(Singleton.getInstance().getResourceBundle().getString("validation"));
		add(mValidate);
		mValidate.add(new ValidateSchemaAction());
		
		
	}
	
}


