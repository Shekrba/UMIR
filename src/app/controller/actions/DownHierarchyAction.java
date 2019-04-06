package app.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import app.view.MyJTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableRowSorter;

import app.Singleton;
import app.model.Table;
import app.model.attribute.Attribute;
import app.view.MyTableModel;
import app.view.TableModelGetter;

public class DownHierarchyAction extends AbstractAction{
	
	public DownHierarchyAction() {
		
		putValue(NAME, Singleton.getInstance().getResourceBundle().getString("down hierarchy"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_DOWN);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift D"));
		putValue(SMALL_ICON, new ImageIcon("images/down.png"));
		putValue(LARGE_ICON_KEY, new ImageIcon("images/downL.png"));
		putValue(SHORT_DESCRIPTION, Singleton.getInstance().getResourceBundle().getString("down hierarchy"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Table currentTable = Singleton.getInstance().getMainFrame().getShowingParrent();
		if(currentTable != null){
			if(!currentTable.getChildren().isEmpty()){
				JTabbedPane tPane = Singleton.getInstance().getMainFrame().getTabbedPane();	
				int index = tPane.getSelectedIndex();
				Table table = currentTable.getChildren().get(index);
				if(!table.getChildren().isEmpty()){		            		
  
					Singleton.getInstance().getMainFrame().setShowingParrent(table);
            		Singleton.getInstance().getMainFrame().setParrentModel(TableModelGetter.getTableModel(table));	          		
            		
            		Singleton.getInstance().getMainFrame().getParrentTable().setModel(TableModelGetter.getTableModel(table));
            		Singleton.getInstance().getMainFrame().getRight().setBottomComponent(Singleton.getInstance().getMainFrame().getTabbedPane());
           		
            		Singleton.getInstance().getMainFrame().getRight().setDividerLocation(300);
            		
            		Singleton.getInstance().getMainFrame().getTabbedPane().removeAll();
            		JTabbedPane tabbedPane = Singleton.getInstance().getMainFrame().getTabbedPane();		            		            
            		
            		
            		Singleton.getInstance().getMainFrame().getParrentName().removeAll();
            		Singleton.getInstance().getMainFrame().getParrentName().add(table.getName(), Singleton.getInstance().getMainFrame().getParrentScroll());
            		
            		
            		for(Table t : table.getChildren()){
            			MyTableModel childModel = TableModelGetter.getTableModel(t);
            			MyJTable childTable = MyJTable.create(childModel);
            			TableRowSorter<MyTableModel> sorter= new TableRowSorter<MyTableModel>(childModel);
            			childTable.setRowSorter(sorter);
            			
            			JScrollPane js = new JScrollPane();
            			js.setViewportView(childTable);
            			
            			tabbedPane.add(t.getName(),js);
            			
		
            		}
            			            		
            	}
            	else {
            	
            		Singleton.getInstance().getMainFrame().setShowingParrent(table);
            		Singleton.getInstance().getMainFrame().setParrentModel(TableModelGetter.getTableModel(table));
            		
            		Singleton.getInstance().getMainFrame().getParrentTable().setModel(TableModelGetter.getTableModel(table));
            		Singleton.getInstance().getMainFrame().getRight().setBottomComponent(null);
            		
            		Singleton.getInstance().getMainFrame().getParrentName().removeAll();
            		Singleton.getInstance().getMainFrame().getParrentName().add(table.getName(), Singleton.getInstance().getMainFrame().getParrentScroll());
	            }
				

				
				TableRowSorter<MyTableModel> parentSorter= new TableRowSorter<MyTableModel>(Singleton.getInstance().getMainFrame().getParrentModel());
    			Singleton.getInstance().getMainFrame().getParrentTable().setRowSorter(parentSorter);
            }
		}
	}
}
