package app.view.numberComponent;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.Singleton;
import app.model.attribute.Attribute;
import app.model.attribute.DateType;
import app.model.attribute.NumberType;
import app.model.attribute.StringType;

public class CreateNumberComponent extends NumberComponent{

	
	


	public CreateNumberComponent(Attribute a) {
		attribute = a;
	
		tf= new JTextField();
		
		tf.setPreferredSize(new Dimension(125,25));
		tf.setMaximumSize(new Dimension(125,25));
		tf.setMinimumSize(new Dimension(125,25));
		
		try {
			String f=Float.toString(((NumberType)attribute.getType()).getPreDef());
			tf.setText(f);
		}catch(NullPointerException e) {
			tf.setText("");
		}	
			
		text=new JLabel(attribute.getName() + Singleton.getInstance().getResourceBundle().getString("value"));
		
		NumberType numberType = (NumberType)attribute.getType();
		
		if(numberType.isReq() ) {
			
			text.setText(text.getText() + " *");
			
		}
		
		if(numberType.getPreDef() != null){
			tf.setText((numberType.getPreDef()).toString());
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
		
		NumberType numberType = (NumberType)attribute.getType();
		
		if(numberType.isReq() ) {
			
			String trimed = tf.getText().trim();
			
				if(trimed.isEmpty()) {
					return false;
				}
			
			
		}
		flag=true;
		
		if((tf.getText()).trim().equals(""))
			return true;
		
		try{
			Float.parseFloat(tf.getText());
		}
		catch (Exception e) {
			flag = false;
		}
		
		String niz[] = tf.getText().split("\\.");
		
		if(niz.length == 2){
			if(!(niz[1].length() <= ((NumberType)attribute.getType()).getDecimal())){
				return false;
			}
		}
		
		return flag;
	}

}
