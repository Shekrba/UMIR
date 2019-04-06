package app.view.boolComponent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import app.Singleton;
import app.model.attribute.Attribute;
import app.model.attribute.BoolType;
import app.model.attribute.StringType;

public class CreateBoolComponent extends BoolComponent {
	
	public CreateBoolComponent(Attribute a) {
		attribute = a;
		cb = new JCheckBox(attribute.getName() + Singleton.getInstance().getResourceBundle().getString("value"));
		BoolType boolType = (BoolType)attribute.getType();
		cb.setSelected(boolType.isPreDef());
		
		if(boolType.isPreDef() != null){
			cb.setSelected(boolType.isPreDef());
		}
		
		BoxLayout boxcbl=new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(boxcbl);
		this.add(Box.createHorizontalGlue());
		this.add(cb);
		this.add(Box.createHorizontalGlue());
	}
	
	@Override
	public boolean jeValidan() {
		return true;
	}
}
