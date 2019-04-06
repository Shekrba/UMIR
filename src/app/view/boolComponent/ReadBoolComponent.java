package app.view.boolComponent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import app.Singleton;
import app.model.attribute.Attribute;
import app.model.attribute.BoolType;

public class ReadBoolComponent extends BoolComponent {
	
	private JCheckBox filter;
	
	public ReadBoolComponent(Attribute a) {
		attribute = a;
		cb = new JCheckBox(attribute.getName() + Singleton.getInstance().getResourceBundle().getString("value"));
		BoolType boolType = (BoolType)attribute.getType();
		cb.setSelected(boolType.isPreDef());
		
		filter = new JCheckBox(Singleton.getInstance().getResourceBundle().getString("ignore"));
		filter.setSelected(true);
		
		BoxLayout boxcbl=new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(boxcbl);
		this.add(Box.createHorizontalGlue());
		this.add(cb);
		this.add(Box.createHorizontalStrut(20));
		this.add(filter);
		this.add(Box.createHorizontalGlue());
	}

	@Override
	public boolean jeValidan() {
		return true;
	}
	
	public JCheckBox getFilter() {
		return filter;
	}

	public void setFilter(JCheckBox filter) {
		this.filter = filter;
	}
	
	
}
