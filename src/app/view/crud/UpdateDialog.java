package app.view.crud;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import app.Singleton;
import app.model.attribute.Attribute;

public class UpdateDialog extends JDialog{

	public UpdateDialog(){
		super(Singleton.getInstance().getMainFrame());
		UFactory factory=(UFactory) CRUDFactoryMaker.getFactory("UPDATE", new ArrayList<Attribute>(Singleton.getInstance().getMainFrame().getShowingParrent().getAttributes().values()));
		
		
		JScrollPane sp = new JScrollPane();
		JPanel p = new JPanel();
		
		p.revalidate();
		p.repaint();
		
		sp.setViewportView(factory.generateComps());
		
		sp.setPreferredSize(new Dimension(1000, 300));
		
		p.add(sp);
		
		this.add(p,BorderLayout.NORTH);
		
		JPanel south = new JPanel();
		JButton btnOK = new JButton();
		btnOK.setText(Singleton.getInstance().getResourceBundle().getString("ok"));
		JButton btnCancel = new JButton();
		btnCancel.setText(Singleton.getInstance().getResourceBundle().getString("cancel"));
		btnOK.setPreferredSize(new Dimension(100, 30));
		btnOK.setMinimumSize(new Dimension(100, 30));
		btnOK.setMaximumSize(new Dimension(100, 30));
		btnCancel.setPreferredSize(new Dimension(100, 30));
		btnCancel.setMinimumSize(new Dimension(100, 30));
		btnCancel.setMaximumSize(new Dimension(100, 30));
		south.add(btnOK);
		south.add(btnCancel);
		
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(factory.jeValidan()){
					boolean flag=Singleton.getInstance().getDbHandler().updateData(factory.getActionComps());
					if(flag)
						UpdateDialog.this.setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(null,Singleton.getInstance().getResourceBundle().getString("not valid"));
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				UpdateDialog.this.setVisible(false);
			}
		});
		
		this.add(south, BorderLayout.SOUTH);
		
		this.setSize(1000, 400);
		this.setLocationRelativeTo(this.getOwner());
		this.setModal(true);
		this.setVisible(true);
	}
	
}
