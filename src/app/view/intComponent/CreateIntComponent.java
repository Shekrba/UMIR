package app.view.intComponent;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.Singleton;
import app.model.attribute.Attribute;
import app.model.attribute.IntType;

public class CreateIntComponent extends IntComponent {
	
	public CreateIntComponent(Attribute a) {
		attribute = a;
	
		tf= new JTextField();
		
		tf.setPreferredSize(new Dimension(125,25));
		tf.setMaximumSize(new Dimension(125,25));
		tf.setMinimumSize(new Dimension(125,25));
		
		tf.setText(Float.toString(((IntType)attribute.getType()).getPreDef()));
		text=new JLabel(attribute.getName() + Singleton.getInstance().getResourceBundle().getString("value"));
		
		IntType intType = (IntType)attribute.getType();
		
		if(intType.isReq() ) {
			
			text.setText(text.getText() + " *");
			
		}
		
		if(intType.getPreDef() != null){
			tf.setText((intType.getPreDef()).toString());
		}
		
		BoxLayout boxTfl=new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(boxTfl);
		this.add(Box.createHorizontalGlue());
		this.add(text);
		this.add(Box.createHorizontalStrut(10));
		this.add(tf);
		this.add(Box.createHorizontalGlue());
		
		tf.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if (!(Character.isDigit(c) ||
	                (c == KeyEvent.VK_BACK_SPACE) ||
	                (c == KeyEvent.VK_DELETE) || 
	                c == '.' )) {
	                  e.consume();
	                }
	           }
	         });
		
		
	}
	
	
	
	@Override
	public boolean jeValidan() {
		boolean flag=false;
		
		IntType intType = (IntType)attribute.getType();
		
		if(intType.isReq() ) {
			
			String trimed = tf.getText().trim();
			
				if(trimed.isEmpty()) {
					return false;
				}
			
			
		}
		flag=true;
		
		
		if((tf.getText()).trim().equals(""))
			return true;
		
		
		try{
			Integer.parseInt(tf.getText());
		}
		catch (Exception e) {
			flag = false;
		}
		
		return flag;
	}
}
