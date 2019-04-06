package app;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JTree;
import javax.swing.UIManager;

import org.everit.json.schema.Schema;

import app.database.DBConnectionHandler;
import app.model.Table;
import app.view.MainFrame;
import schema.view.SchemaDialog;

public class Singleton {

	
	public static final String APP_NAME = "UMIR";

	private static Singleton instance = new Singleton();

	private MainFrame mainFrame;
	
	private HashMap<String, Table> allTables;
	
	private SchemaDialog schemaDialog;
	
	private org.everit.json.schema.Schema metaSchema;
	
	private app.model.Schema schema;
	
	private DBConnectionHandler dbHandler;
	
	private ResourceBundle resourceBundle;
	
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

	public DBConnectionHandler getDbHandler() {
		return dbHandler;
	}

	public void setDbHandler(DBConnectionHandler dbHandler) {
		this.dbHandler = dbHandler;
	}

	private Table table;
	

	public app.model.Schema getSchema() {
		return schema;
	}

	public void setSchema(app.model.Schema schema) {
		this.schema = schema;
	}
	
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Schema getMetaSchema() {
		return metaSchema;
	}

	public void setMetaSchema(Schema metaSchema) {
		this.metaSchema = metaSchema;
	}

	public SchemaDialog getSchemaDialog() {
		return schemaDialog;
	}

	public void setSchemaDialog(SchemaDialog schemaDialog) {
		this.schemaDialog = schemaDialog;
	}
	
	
	public static Singleton getInstance() {
		return instance;
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public HashMap<String, Table> getAllTables() {
		return allTables;
	}

	public void setAllTables(HashMap<String, Table> allTables) {
		this.allTables = allTables;
	}
	
	public void changeLanguage(){
		
		UIManager.put("OptionPane.yesButtonText", resourceBundle.getString("yesOption"));
		UIManager.put("OptionPane.noButtonText", resourceBundle.getString("noOption"));
		UIManager.put("OptionPane.okButtonText", resourceBundle.getString("okOption"));
		UIManager.put("OptionPane.cancelButtonText", resourceBundle.getString("cancelOption"));	
		resourceBundle =ResourceBundle.getBundle( "MessageResources.MessageResources", Locale.getDefault());
		
		mainFrame.init();
			
			
			
	}
	
}
