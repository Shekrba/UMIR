package schema.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import app.Singleton;



public class JSONHandler extends Handler{

	
	public JSONHandler(JEditorPane text) {
		super(text);
		setText("{}");
	}

	
	
	



	@Override
	public void save(String t, File file) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		PrintWriter pw = new PrintWriter(file);
		pw.print(t);
		pw.flush();
		pw.close();
		
	}



	@Override
	public void load(File file) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));
		JSONTokener tokener = new JSONTokener(br);
		JSONObject o=new JSONObject();
		try {
			o = new JSONObject(tokener);
		}catch(JSONException e) {
			JOptionPane.showMessageDialog(null,"Fajl nije u JSON formatu", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		setText(o.toString(2));
		br.close();
		
	}
	
}
