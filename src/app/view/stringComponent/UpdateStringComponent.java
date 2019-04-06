package app.view.stringComponent;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.Singleton;
import app.model.Table;
import app.model.attribute.Attribute;
import app.model.attribute.StringType;

public class UpdateStringComponent extends StringComponent {

	

	public UpdateStringComponent(Attribute a, String currentTxt) {
		
		attribute = a;
		
		tf= new JTextField();
		
		tf.setPreferredSize(new Dimension(125,25));
		tf.setMaximumSize(new Dimension(125,25));
		tf.setMinimumSize(new Dimension(125,25));
		
		tf.setText(currentTxt);
		text=new JLabel(attribute.getName() + Singleton.getInstance().getResourceBundle().getString("value"));
		
		StringType stringType = (StringType)attribute.getType();
		
		if(stringType.isReq() ) {
			
			text.setText(text.getText() + " *");
			
		}
		
		
		
		ArrayList<String> keys = Singleton.getInstance().getMainFrame().getShowingParrent().getPrimaryKey();
		
		if(keys.contains(a.getCode())){
			tf.setEnabled(false);
		}
		
		BoxLayout boxl=new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(boxl);
		this.add(Box.createHorizontalGlue());
		this.add(text);
		this.add(Box.createHorizontalStrut(10));
		this.add(tf);
		this.add(Box.createHorizontalGlue());
		
		int lenght =((StringType)attribute.getType()).getMaxLength();
		tf.addKeyListener(new java.awt.event.KeyAdapter() {
		    public void keyTyped(java.awt.event.KeyEvent evt) {
		        if(tf.getText().length()>lenght-1 &&!(evt.getKeyChar()==KeyEvent.VK_DELETE||evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep(); 
		            evt.consume();
		         }
		        
		        
		    }
		});
		
		
	}
	
	@Override
	public boolean jeValidan() {
		boolean flag=false;
		if(((StringType)attribute.getType()).isReq() ) {
			
			String trimed = tf.getText().trim();
			
				if(trimed.isEmpty()) {
					return false;
				}
			
			
		}
		flag=true;
		
		if(tf.getText().length() > ((StringType)attribute.getType()).getMaxLength())
			flag = false;
		
		return flag;
	}	
	
	
	
}
