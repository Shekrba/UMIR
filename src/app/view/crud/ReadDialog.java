package app.view.crud;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import app.view.MyJTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import app.Singleton;
import app.model.Table;
import app.model.attribute.Attribute;
import app.view.MyTableModel;
import app.view.TableModelGetter;

public class ReadDialog extends JDialog{

	public ReadDialog(){
		super(Singleton.getInstance().getMainFrame());//
		RFactory factory=(RFactory) CRUDFactoryMaker.getFactory("READ", new ArrayList<Attribute>(Singleton.getInstance().getMainFrame().getShowingParrent().getAttributes().values()));
		
		this.setResizable(false);
		
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
					
					java.util.List<RowFilter<MyTableModel,Object>> filters = new ArrayList<RowFilter<MyTableModel,Object>>();
					
					Table table = Singleton.getInstance().getMainFrame().getShowingParrent();
					MyJTable jtable = Singleton.getInstance().getMainFrame().getParrentTable();
					
					MyTableModel tableModel = TableModelGetter.getTableModel(table);
					
					jtable.setRowSorter(new TableRowSorter<MyTableModel>(tableModel));
					
					ArrayList<JComponent> jcomponents = factory.getJcomponents();
					ArrayList<Attribute> attributes = new ArrayList(Singleton.getInstance().getMainFrame().getShowingParrent().getAttributes().values());
					
					int i = 0;
					
					for (Attribute a : attributes) {
						
						Integer x = tableModel.getIndexByName(a.getName());
						
						if(a.getType().getTypeName().equals("varchar") ||
								a.getType().getTypeName().equals("char")){
							String keyVal = ((JTextField) jcomponents.get(i)).getText();
							if(!(keyVal.trim()).equals("")){
								filters.add(RowFilter.regexFilter(keyVal, x)); 
							}							
						}
						else if(a.getType().getTypeName().equals("double")){
							
							String keyVal1 = ((JTextField) jcomponents.get(i)).getText();
							
							if(!(keyVal1.trim()).equals("")){
								Number numb1 = Double.parseDouble(keyVal1);				            
					            List<RowFilter<Object, Object>> from = new ArrayList<RowFilter<Object, Object>>(2);

					            from.add(0,RowFilter.numberFilter(ComparisonType.EQUAL, numb1,x));
					            from.add(0,RowFilter.numberFilter(ComparisonType.AFTER, numb1,x));

					            filters.add(RowFilter.orFilter(from));							
							}																	
							
							i++;
							
							String keyVal2 = ((JTextField) jcomponents.get(i)).getText();
							
							if(!(keyVal2.trim()).equals("")){
								Number numb2 = Double.parseDouble(keyVal2);				            
					            List<RowFilter<Object, Object>> to = new ArrayList<RowFilter<Object, Object>>(2);

					            to.add(0,RowFilter.numberFilter(ComparisonType.EQUAL, numb2,x));
					            to.add(0,RowFilter.numberFilter(ComparisonType.BEFORE, numb2,x));

					            filters.add(RowFilter.orFilter(to));								
							}													
						}
						else if(a.getType().getTypeName().equals("int")){
							
							String keyVal1 = ((JTextField) jcomponents.get(i)).getText();
							
							if(!(keyVal1.trim()).equals("")){
								Number numb1 = Integer.parseInt(keyVal1);				            
					            List<RowFilter<Object, Object>> from = new ArrayList<RowFilter<Object, Object>>(2);

					            from.add(0,RowFilter.numberFilter(ComparisonType.EQUAL, numb1,x));
					            from.add(0,RowFilter.numberFilter(ComparisonType.AFTER, numb1,x));

					            filters.add(RowFilter.orFilter(from));							
							}																	
		
							i++;
							
							String keyVal2 = ((JTextField) jcomponents.get(i)).getText();
							
							if(!(keyVal2.trim()).equals("")){
								Number numb2 = Integer.parseInt(keyVal2);				            
					            List<RowFilter<Object, Object>> to = new ArrayList<RowFilter<Object, Object>>(2);

					            to.add(0,RowFilter.numberFilter(ComparisonType.EQUAL, numb2,x));
					            to.add(0,RowFilter.numberFilter(ComparisonType.BEFORE, numb2,x));

					            filters.add(RowFilter.orFilter(to));								
							}
						}						
						else if(a.getType().getTypeName().equals("date")){
							
							String keyVal1 = ((JTextField) jcomponents.get(i)).getText();
							
							DateFormat formater = new SimpleDateFormat(Singleton.getInstance().getResourceBundle().getString("dateFormat"));
							
							if(!(keyVal1.trim()).equals("")){
								
								try {
									Date date1 = formater.parse(keyVal1);			            
						            List<RowFilter<Object, Object>> from = new ArrayList<RowFilter<Object, Object>>(2);

						            from.add(0,RowFilter.dateFilter(ComparisonType.EQUAL, date1,x));
						            from.add(0,RowFilter.dateFilter(ComparisonType.AFTER, date1,x));

						            filters.add(RowFilter.orFilter(from));
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}							
							}	
							
							i++;
							
							String keyVal2 = ((JTextField) jcomponents.get(i)).getText();
							
							if(!(keyVal2.trim()).equals("")){
								
								try {
									Date date2 = formater.parse(keyVal2);			            
						            List<RowFilter<Object, Object>> to = new ArrayList<RowFilter<Object, Object>>(2);

						            to.add(0,RowFilter.dateFilter(ComparisonType.EQUAL, date2,x));
						            to.add(0,RowFilter.dateFilter(ComparisonType.BEFORE, date2,x));

						            filters.add(RowFilter.orFilter(to));
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}							
							}	
						}
						else if(a.getType().getTypeName().equals("boolean")){
							
							if(!((JCheckBox)jcomponents.get(i+1)).isSelected()){
								String keyVal;
								if(((JCheckBox)jcomponents.get(i)).isSelected())
									keyVal = "true";
								else
									keyVal = "false";	
								filters.add(RowFilter.regexFilter(keyVal, x));
							}
								 
								
							i++;
							
						}
						i++;
					}
					
					RowFilter<MyTableModel,Object> rf=RowFilter.andFilter(filters);
		
					TableRowSorter<MyTableModel> sorter=(TableRowSorter<MyTableModel>) jtable.getRowSorter();
					
					if(sorter!=null){
						sorter.setRowFilter(rf);
					}
					else
						System.out.println("ne radi");
						
					ReadDialog.this.setVisible(false);
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
				
				ReadDialog.this.setVisible(false);
			}
		});
		
		this.add(south, BorderLayout.SOUTH);
		
		this.setSize(1000, 400);
		this.setLocationRelativeTo(this.getOwner());
		this.setModal(true);
		this.setVisible(true);
	}
	
}
