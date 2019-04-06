package schema.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class DBHandler extends Handler {

	public DBHandler(JEditorPane text) {
		super(text);
		setText("");
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
				new FileInputStream(file),"UTF-8"));
		String line=null;
		String txt="";
		while((line = br.readLine()) != null) {
            txt+=line+"\n";
        }   
		setText(txt);
		br.close();
		
	}

}
