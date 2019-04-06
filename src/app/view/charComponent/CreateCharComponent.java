package app.view.charComponent;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.Singleton;
import app.model.attribute.Attribute;
import app.model.attribute.CharType;

public class CreateCharComponent extends CharComponent {

	public CreateCharComponent(Attribute a) {
		
		attribute = a;

		tf= new JTextField();
		
		tf.setPreferredSize(new Dimension(125,25));
		tf.setMaximumSize(new Dimension(125,25));
		tf.setMinimumSize(new Dimension(125,25));
		
		tf.setMinimumSize(new Dimension(25,125));
		tf.setSize(new Dimension(25,125));
		tf.setText(((CharType)attribute.getType()).getPreDef());
		text=new JLabel(attribute.getName() + Singleton.getInstance().getResourceBundle().getString("value"));
		
		CharType charType = (CharType)attribute.getType();
		
		if(charType.isReq() ) {
			
			text.setText(text.getText() + " *");
			
		}
		
		if(charType.getPreDef() != null){
			tf.setText(charType.getPreDef());
		}
		
		BoxLayout boxTfl=new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(boxTfl);
		this.add(Box.createHorizontalGlue());
		this.add(text);
		this.add(Box.createHorizontalStrut(10));
		this.add(tf);
		this.add(Box.createHorizontalGlue());
		
		int lenght =((CharType)attribute.getType()).getLength();
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
		String trimed = tf.getText().trim();
		if(((CharType)attribute.getType()).isReq() ) {
			
			
				if(trimed.isEmpty()) {
					return false;
				}
			
			
		}
		flag=true;
		
		if((tf.getText().length() != ((CharType)attribute.getType()).getLength()) && (tf.getText().length() != 0))
			flag = false;
			
		return flag;
	}
}
