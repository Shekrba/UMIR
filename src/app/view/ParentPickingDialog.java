package app.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.Singleton;
import app.model.Table;

public class ParentPickingDialog extends JDialog {
	
	private JComboBox<String> comboBox;
	private boolean flag;
	private int index;
	
	public ParentPickingDialog() {
		
		super(Singleton.getInstance().getMainFrame(),"Choose Parent");
		
		flag = false;
		
		setTitle("Choose Parent");
		
		JLabel l = new JLabel("Parent: ");
		this.add(l);
		
		comboBox = new JComboBox<String>();
		
		Table currentTable = Singleton.getInstance().getMainFrame().getShowingParrent();
		for(Table t : currentTable.getParent()){
			comboBox.addItem(t.getName());
		}
		
		JPanel center = new JPanel();
		JPanel cblPanel = new JPanel();
		comboBox.setPreferredSize(new Dimension(125,25));
		comboBox.setMaximumSize(new Dimension(125,25));
		comboBox.setMinimumSize(new Dimension(125,25));
		
		BoxLayout boxTfl=new BoxLayout(cblPanel,BoxLayout.X_AXIS);
		cblPanel.setLayout(boxTfl);
		cblPanel.add(Box.createHorizontalGlue());
		cblPanel.add(l);
		cblPanel.add(comboBox);
		cblPanel.add(Box.createHorizontalGlue());
		
		BoxLayout boxCenter=new BoxLayout(center,BoxLayout.Y_AXIS);
		center.setLayout(boxCenter);
		center.add(Box.createVerticalGlue());
		center.add(cblPanel);
		center.add(Box.createVerticalGlue());
		
		this.add(center, BorderLayout.CENTER);
		
		JPanel pnlOKCancel = new JPanel();
		
		JButton btnOK = new JButton();
		btnOK.setText("OK");
		JButton btnCancel = new JButton();
		btnCancel.setText("Cancel");
		btnOK.setPreferredSize(new Dimension(80, 30));
		btnOK.setMinimumSize(new Dimension(80, 30));
		btnOK.setMaximumSize(new Dimension(80, 30));
		btnCancel.setPreferredSize(new Dimension(80, 30));
		btnCancel.setMinimumSize(new Dimension(80, 30));
		btnCancel.setMaximumSize(new Dimension(80, 30));
		pnlOKCancel.add(btnOK);
		pnlOKCancel.add(btnCancel);
		
		this.add(pnlOKCancel, BorderLayout.SOUTH);
		this.setSize(new Dimension(400, 200));
		setLocationRelativeTo(this.getOwner());
		
		btnCancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				flag = false;
				setVisible(false);
			}
		});
		
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				index = comboBox.getSelectedIndex();
				
				flag = true;
				setVisible(false);				
			}
		});
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
