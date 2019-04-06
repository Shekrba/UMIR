package app.view.dateComponent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.model.attribute.Attribute;
import app.view.crud.IValid;


public abstract class DateComponent extends JPanel implements IValid {
	
	protected DatePicker dp;
	protected JLabel text;
	protected Attribute attribute;
	protected JButton button;
	protected JTextField tf;
	public DatePicker getDp() {
		return dp;
	}
	public void setDp(DatePicker dp) {
		this.dp = dp;
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
	public JButton getButton() {
		return button;
	}
	public void setButton(JButton button) {
		this.button = button;
	}
	public JTextField getTf() {
		return tf;
	}
	public void setTf(JTextField tf) {
		this.tf = tf;
	}
	
}
