package schema.validators;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.everit.json.schema.ValidationException;
import org.json.JSONException;
import org.json.JSONObject;

import app.Singleton;
import schema.view.SchemaDialog;

public class JSONValidator implements Validator{

	@Override
	public JSONObject validate(String s) {
		JSONObject ret=null;
		String text;
		text=s;
		JSONObject json=new JSONObject();
		
		try {
			json=new JSONObject(text);
		} catch (JSONException e) {
		    JOptionPane.showMessageDialog(null,Singleton.getInstance().getResourceBundle().getString("input is not in json format"), Singleton.getInstance().getResourceBundle().getString("error"), JOptionPane.ERROR_MESSAGE);
		    return ret;
		}
		try {
			Singleton.getInstance().getMetaSchema().validate(json);
			ret=json;
		} catch (ValidationException e) {
			JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setPreferredSize(new Dimension(200,100));
	  
	        JTextArea textArea = new JTextArea(e.getAllMessages().toString());
	        textArea.setLineWrap(true);
	        textArea.setWrapStyleWord(true);
	        scrollPane.getViewport().setView(textArea);
	        JOptionPane.showMessageDialog(null,
	                                               scrollPane,
	                                               Singleton.getInstance().getResourceBundle().getString("validation error"),
	                                               JOptionPane.ERROR_MESSAGE);
	        ret=null;
		} 
		return ret;
		
	}

}
