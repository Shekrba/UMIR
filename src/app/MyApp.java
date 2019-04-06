package app;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.UIManager;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import app.database.HeidiSqlDb;
import app.model.SchemaComponent;
import app.view.MainFrame;


public class MyApp {
	
	public static void main(String[] args) throws FileNotFoundException {
	
		app.model.Package pcg=new app.model.Package(new ArrayList<SchemaComponent>());
		pcg.setName("Default");
		app.model.Schema sc=new app.model.Schema("Default",pcg,"");
		Singleton.getInstance().setSchema(sc);
		Locale.setDefault(new Locale("sr","RS"));
		ResourceBundle resourceBundle=ResourceBundle.getBundle( "MessageResources.MessageResources", Locale.getDefault());
		ResourceBundle.getBundle( "MessageResources.MessageResources", Locale.getDefault());
		UIManager.put("OptionPane.yesButtonText", resourceBundle.getString("yesOption"));
		UIManager.put("OptionPane.noButtonText", resourceBundle.getString("noOption"));
		UIManager.put("OptionPane.okButtonText", resourceBundle.getString("okOption"));
		UIManager.put("OptionPane.cancelButtonText", resourceBundle.getString("cancelOption"));
		Singleton.getInstance().setResourceBundle(resourceBundle);
		
		Singleton.getInstance().setDbHandler(new HeidiSqlDb());
		
		MainFrame mf = new MainFrame();
	
		Singleton.getInstance().setMainFrame(mf);
		
		InputStream inputStream = new BufferedInputStream(new FileInputStream(new File("meta/schema.json")));
		JSONObject metaSchema = new JSONObject(new JSONTokener(inputStream));
		Schema schema = SchemaLoader.load(metaSchema);
		Singleton.getInstance().setMetaSchema(schema);
		
		
		mf.setVisible(true);
	}
}