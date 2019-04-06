package app.view.crud;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import app.view.MyJTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import app.Singleton;
import app.model.ForeignKey;
import app.view.MyTableModel;
import app.view.TableModelGetter;

public class ZoomDialog extends JDialog{

	public ZoomDialog(ForeignKey fk,HashMap<String,JComponent> comps) {
		super(Singleton.getInstance().getMainFrame());
		
		this.setTitle(fk.getReferencedTable());
		
		JScrollPane jsp=new JScrollPane();
		
		MyTableModel tm=TableModelGetter.getTableModel(Singleton.getInstance().getAllTables().get(fk.getReferencedTable()));
		MyJTable jtable=MyJTable.create(tm);
		jsp.setViewportView(jtable);
		this.add(jsp,BorderLayout.NORTH);
		
		JPanel p=new JPanel();
		JButton btnOk=new JButton(Singleton.getInstance().getResourceBundle().getString("ok"));
		JButton btnCancel=new JButton(Singleton.getInstance().getResourceBundle().getString("cancel"));
		
		btnOk.setPreferredSize(new Dimension(100, 30));
		btnOk.setMinimumSize(new Dimension(100, 30));
		btnOk.setMaximumSize(new Dimension(100, 30));
		btnCancel.setPreferredSize(new Dimension(100, 30));
		btnCancel.setMinimumSize(new Dimension(100, 30));
		btnCancel.setMaximumSize(new Dimension(100, 30));
		
		p.add(btnOk);
		p.add(btnCancel);
		
		btnOk.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int i=0;
				for (String fkAttr : fk.getReferencinAttr()){
					JComponent c=comps.get(fkAttr);
					String name=Singleton.getInstance().getAllTables().get(fk.getReferencedTable()).getAttributes().get(fk.getReferencedAttr().get(i)).getName();
					if(c instanceof JTextField){
						Object o=tm.getValueAt(jtable.getSelectedRow(), tm.getIndexByName(name));
						String x;
						if(o instanceof Integer){
							x=Integer.toString((Integer)o);
						}else if (o instanceof Date){
							DateFormat format1 = new SimpleDateFormat(Singleton.getInstance().getResourceBundle().getString("dateFormat"));
							x=format1.format((Date)o);
						}else{
							x=(String)o;
						}
						((JTextField) c).setText(x);
					}else{
						((JCheckBox) c).setSelected((Boolean)tm.getValueAt(jtable.getSelectedRow(), tm.getIndexByName(name)));
					}
					i++;
				}
				ZoomDialog.this.setVisible(false);
			}
			
		});
		
		jtable.setPreferredScrollableViewportSize(new Dimension(500, 300));
		jtable.setFillsViewportHeight(true);
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ZoomDialog.this.setVisible(false);
			}
		});
		
		this.add(p,BorderLayout.SOUTH);
		
		
		this.setSize(500, 500);
		
		setLocationRelativeTo(this.getOwner());
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}
	
}
