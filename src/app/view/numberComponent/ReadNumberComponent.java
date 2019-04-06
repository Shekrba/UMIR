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
import app.model.attribute.NumberType;
import app.model.attribute.StringType;

public class ReadNumberComponent extends NumberComponent{

	private JTextField tfTo;
	private JLabel textTo;
	
	public ReadNumberComponent(Attribute a) {
		attribute = a;
		
		tf= new JTextField();
		
		tf.setPreferredSize(new Dimension(125,25));
		tf.setMaximumSize(new Dimension(125,25));
		tf.setMinimumSize(new Dimension(125,25));
		
		text=new JLabel(attribute.getName() + Singleton.getInstance().getResourceBundle().getString("from"));
		
		tfTo = new JTextField();
		
		tfTo.setPreferredSize(new Dimension(125,25));
		tfTo.setMaximumSize(new Dimension(125,25));
		tfTo.setMinimumSize(new Dimension(125,25));
		
		textTo = new JLabel(Singleton.getInstance().getResourceBundle().getString("to"));
		
		
		
		BoxLayout boxl=new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(boxl);
		this.add(Box.createHorizontalGlue());
		this.add(text);
		this.add(Box.createHorizontalStrut(10));
		this.add(tf);
		this.add(Box.createHorizontalStrut(20));
		this.add(textTo);
		this.add(Box.createHorizontalStrut(10));
		this.add(tfTo);
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
		
		tfTo.addKeyListener(new KeyAdapter() {
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
	
	

	public JTextField getTfTo() {
		return tfTo;
	}



	public void setTfTo(JTextField tfTo) {
		this.tfTo = tfTo;
	}



	public JLabel getTextTo() {
		return textTo;
	}



	public void setTextTo(JLabel textTo) {
		this.textTo = textTo;
	}



	@Override
	public boolean jeValidan() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
