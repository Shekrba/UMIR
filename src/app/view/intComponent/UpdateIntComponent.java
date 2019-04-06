package app.view.intComponent;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.Singleton;
import app.model.Table;
import app.model.attribute.Attribute;
import app.model.attribute.IntType;
import app.model.attribute.NumberType;

public class UpdateIntComponent extends IntComponent {
	
	public UpdateIntComponent(Attribute a,Integer currentNumber) {
		
		attribute = a;
		
		text = new JLabel(attribute.getName() + Singleton.getInstance().getResourceBundle().getString("value"));
		
		IntType intType = (IntType)attribute.getType();
		
		if(intType.isReq() ) {
			
			text.setText(text.getText() + " *");
			
		}

		tf= new JTextField();
		
		tf.setPreferredSize(new Dimension(125,25));
		tf.setMaximumSize(new Dimension(125,25));
		tf.setMinimumSize(new Dimension(125,25));
		
		tf.setText(currentNumber.toString());
		
		
		ArrayList<String> keys = Singleton.getInstance().getMainFrame().getShowingParrent().getPrimaryKey();
		
		if(keys.contains(a.getCode())){
			tf.setEnabled(false);
		}
		
		BoxLayout boxtfl=new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(boxtfl);
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
	            		
	            			(c == KeyEvent.VK_DELETE)|| 
	    	                c == '.')) {
	            	
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
