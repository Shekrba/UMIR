package app.view.boolComponent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.model.attribute.Attribute;
import app.view.crud.IValid;

public abstract class BoolComponent extends JPanel implements IValid {
	
	protected JCheckBox cb;
	protected JLabel text;
	protected Attribute attribute;
	
	public JCheckBox getCb() {
		return cb;
	}
	public void setCb(JCheckBox cb) {
		this.cb = cb;
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
