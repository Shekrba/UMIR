package schema.handlers;

import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.swing.JEditorPane;
import javax.swing.text.Document;

public abstract class Handler extends Observable{

	protected JEditorPane text;
	
	abstract public void save(String t,File f)throws IOException;
	abstract public void load(File f)throws IOException;
	
	public Handler(JEditorPane text) {
		this.text=text;
	}
	
	public void setText(String text) {
		this.text.setText(text);
	}
	
	public String getText() {
		return text.getText();
	}
	
}
