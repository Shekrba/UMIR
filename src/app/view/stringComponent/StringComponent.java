package app.view.stringComponent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.model.attribute.Attribute;
import app.view.crud.IValid;

abstract public class StringComponent extends JPanel implements IValid {
	
	protected JTextField tf;
	protected JLabel text;
	protected Attribute attribute;
	public JTextField getTf() {
		return tf;
	}
	public void setTf(JTextField tf) {
		this.tf = tf;
	}
	public JLabel getText() {
		return text;
	}
	public void setText(JLabel text) {
		this.text = text;
	}
	public Attribute getAttribute() {
		return attribute;
	}
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	
	
	
	

}
