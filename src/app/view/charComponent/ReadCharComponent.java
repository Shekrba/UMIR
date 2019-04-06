package app.view.charComponent;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.Singleton;
import app.model.attribute.Attribute;

public class ReadCharComponent extends CharComponent {
	public ReadCharComponent(Attribute a) {
		
		attribute = a;
			
		tf= new JTextField();
		
		tf.setPreferredSize(new Dimension(125,25));
		tf.setMaximumSize(new Dimension(125,25));
		tf.setMinimumSize(new Dimension(125,25));
		
		text=new JLabel(attribute.getName() + Singleton.getInstance().getResourceBundle().getString("value"));
		
		BoxLayout boxcbl=new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(boxcbl);
		this.add(Box.createHorizontalGlue());
		this.add(text);
		this.add(Box.createHorizontalStrut(10));
		this.add(tf);
		this.add(Box.createHorizontalGlue());
	
	}
	
	@Override
	public boolean jeValidan() {
		return true;
	}
}
