package app.view.dateComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.Singleton;
import app.model.Table;
import app.model.attribute.Attribute;
import app.model.attribute.DateType;

public class UpdateDateComponent extends DateComponent {
	public UpdateDateComponent(Attribute a, Date currentDate) {
		attribute = a;
		DateFormat formater=new SimpleDateFormat(Singleton.getInstance().getResourceBundle().getString("dateFormat"));
		DateType dateType = (DateType)attribute.getType();
		tf = new JTextField();
		
		tf.setPreferredSize(new Dimension(125,25));
		tf.setMaximumSize(new Dimension(125,25));
		tf.setMinimumSize(new Dimension(125,25));
		tf.setEditable(false);
		tf.setBackground(Color.WHITE);
		
		tf.setText(formater.format(currentDate));
		text=new JLabel(attribute.getName() + Singleton.getInstance().getResourceBundle().getString("value"));
		
		if(dateType.isReq() ) {
			
			text.setText(text.getText() + " *");
			
		}
		
		button = new JButton(Singleton.getInstance().getResourceBundle().getString("pick"));
		
		BoxLayout boxdpl=new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(boxdpl);
		this.add(Box.createHorizontalGlue());
		this.add(text);
		this.add(Box.createHorizontalStrut(10));
		this.add(tf);
		this.add(button);
		this.add(Box.createHorizontalGlue());
		
		ArrayList<String> keys = Singleton.getInstance().getMainFrame().getShowingParrent().getPrimaryKey();
		
		if(keys.contains(a.getCode())){
			button.setEnabled(false);
		}
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				dp = new DatePicker(Singleton.getInstance().getMainFrame());
				tf.setText(dp.getL().getText());
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
}
