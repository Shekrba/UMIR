package app.view.dateComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.Singleton;
import app.model.attribute.Attribute;
import app.model.attribute.DateType;

public class ReadDateComponent extends DateComponent {
	
	private JLabel text2;
	private JTextField tf2;
	private JButton button2;
	private DatePicker dp2;
	
	public ReadDateComponent(Attribute a) {
		attribute = a;
		DateType dateType = (DateType)attribute.getType();
		tf = new JTextField();
		
		tf.setPreferredSize(new Dimension(125,25));
		tf.setMaximumSize(new Dimension(125,25));
		tf.setMinimumSize(new Dimension(125,25));
		tf.setEditable(false);
		tf.setBackground(Color.WHITE);
		
		text=new JLabel(attribute.getName() + Singleton.getInstance().getResourceBundle().getString("from"));
		tf2 = new JTextField();
		
		tf2.setPreferredSize(new Dimension(125,25));
		tf2.setMaximumSize(new Dimension(125,25));
		tf2.setMinimumSize(new Dimension(125,25));
		tf2.setEditable(false);
		tf2.setBackground(Color.WHITE);
		
		text2=new JLabel(Singleton.getInstance().getResourceBundle().getString("to"));
		button = new JButton(Singleton.getInstance().getResourceBundle().getString("pick"));
		button2 = new JButton(Singleton.getInstance().getResourceBundle().getString("pick"));
		
		BoxLayout boxdpl=new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(boxdpl);
		this.add(Box.createHorizontalGlue());
		this.add(text);
		this.add(Box.createHorizontalStrut(10));
		this.add(tf);
		this.add(button);
		this.add(Box.createHorizontalStrut(20));
		this.add(text2);
		this.add(Box.createHorizontalStrut(10));
		this.add(tf2);
		this.add(button2);
		this.add(Box.createHorizontalGlue());
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				dp = new DatePicker(Singleton.getInstance().getMainFrame());
				tf.setText(dp.getL().getText());
			}
		});
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				dp2 = new DatePicker(Singleton.getInstance().getMainFrame());
				tf2.setText(dp.getL().getText());
			}
		});
	}
	
	@Override
	public boolean jeValidan() {
		boolean flag=false;
		if(((DateType)attribute.getType()).isReq() ) {
			
			String trimed = tf.getText().trim();
			
				if(trimed.isEmpty()) {
					return false;
				}
			
			
		}
		flag=true;
		
		return flag;
	}

	public JLabel getText2() {
		return text2;
	}

	public void setText2(JLabel text2) {
		this.text2 = text2;
	}

	public JTextField getTf2() {
		return tf2;
	}

	public void setTf2(JTextField tf2) {
		this.tf2 = tf2;
	}

	public JButton getButton2() {
		return button2;
	}

	public void setButton2(JButton button2) {
		this.button2 = button2;
	}

	public DatePicker getDp2() {
		return dp2;
	}

	public void setDp2(DatePicker dp2) {
		this.dp2 = dp2;
	}
	
}
