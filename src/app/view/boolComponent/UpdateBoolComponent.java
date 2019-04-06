package app.view.boolComponent;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import app.Singleton;
import app.model.Table;
import app.model.attribute.Attribute;
import app.model.attribute.BoolType;

public class UpdateBoolComponent extends BoolComponent {
	
	
	public UpdateBoolComponent(Attribute a, boolean currentState) {
		attribute = a;
		cb = new JCheckBox(attribute.getName() + Singleton.getInstance().getResourceBundle().getString("value"));
		//BoolType boolType = (BoolType)attribute.getType();
		cb.setSelected(currentState);
		
		BoxLayout boxcbl=new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(boxcbl);
		this.add(Box.createHorizontalGlue());
		this.add(cb);
		this.add(Box.createHorizontalGlue());
		
		ArrayList<String> keys = Singleton.getInstance().getMainFrame().getShowingParrent().getPrimaryKey();
		
		if(keys.contains(a.getCode())){
			cb.setEnabled(false);
		}
				
		
	}

	@Override
	public boolean jeValidan() {
		return true;
	}
		
	
}
