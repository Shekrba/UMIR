package app.view.stringComponent;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.Singleton;
import app.model.attribute.Attribute;
import app.model.attribute.CharType;
import app.model.attribute.StringType;

public class CreateStringComponent extends StringComponent{

	
	

	public CreateStringComponent(Attribute a) {
		
		attribute = a;

		tf= new JTextField();
		
		tf.setPreferredSize(new Dimension(125,25));
		tf.setMaximumSize(new Dimension(125,25));
		tf.setMinimumSize(new Dimension(125,25));
		
		tf.setMinimumSize(new Dimension(25,125));
		tf.setSize(new Dimension(25,125));
		tf.setText(((StringType)attribute.getType()).getPreDef());
		text=new JLabel(attribute.getName() + Singleton.getInstance().getResourceBundle().getString("value"));
		
		StringType stringType = (StringType)attribute.getType();
		
		if(stringType.isReq() ) {
			
			text.setText(text.getText() + " *");
			
		}
		
		if(stringType.getPreDef() != null){
			tf.setText(stringType.getPreDef());
		}
		
		BoxLayout boxTfl=new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(boxTfl);
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
