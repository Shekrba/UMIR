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
import app.view.MyTableModel;
import app.view.ParentPickingDialog;
import app.view.TableModelGetter;

public class UpHierarchyAction extends AbstractAction{
	
	public UpHierarchyAction() {
		
		putValue(NAME, Singleton.getInstance().getResourceBundle().getString("up hierarchy"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_UP);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift U"));
		putValue(SMALL_ICON, new ImageIcon("images/up.png"));
		putValue(LARGE_ICON_KEY, new ImageIcon("images/upL.png"));
		putValue(SHORT_DESCRIPTION, Singleton.getInstance().getResourceBundle().getString("up hierarchy"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
		Table currentTable = Singleton.getInstance().getMainFrame().getShowingParrent();
		if(currentTable != null){
			if(currentTable.getParent().size() == 1)
				doTheAction(currentTable, 0);				
			else if(currentTable.getParent().size() > 1){
				ParentPickingDialog ppd = new ParentPickingDialog();
				ppd.setModal(true);
				ppd.setVisible(true);			
				if(ppd.isFlag()){
					doTheAction(currentTable, ppd.getIndex());				
				}
			}
		}
	}
	
	
	private void doTheAction(Table currentTable,int index){
		if(!currentTable.getParent().get(index).getChildren().isEmpty()){
			Table ct= currentTable.getParent().get(index);
			Singleton.getInstance().getMainFrame().setShowingParrent(ct);
			Singleton.getInstance().getMainFrame().setParrentModel(TableModelGetter.getTableModel(ct));
			MyTableModel tableModel = Singleton.getInstance().getMainFrame().getParrentModel();		          		
			
			Singleton.getInstance().getMainFrame().getParrentTable().setModel(tableModel);
			Singleton.getInstance().getMainFrame().getRight().setBottomComponent(Singleton.getInstance().getMainFrame().getTabbedPane());
			Singleton.getInstance().getMainFrame().getChildTable().setModel(tableModel);
			Singleton.getInstance().getMainFrame().getRight().setDividerLocation(300);
			
			Singleton.getInstance().getMainFrame().getTabbedPane().removeAll();
			JTabbedPane tabbedPane = Singleton.getInstance().getMainFrame().getTabbedPane();		            		            
			
			
			Singleton.getInstance().getMainFrame().getParrentName().removeAll();
			Singleton.getInstance().getMainFrame().getParrentName().add(ct.getName(), Singleton.getInstance().getMainFrame().getParrentScroll());
			
			for(Table t : ct.getChildren()){
				MyTableModel childModel =TableModelGetter.getTableModel(t);
				MyJTable childTable = MyJTable.create(childModel);
				TableRowSorter<MyTableModel> sorter= new TableRowSorter<MyTableModel>(childModel);
    			childTable.setRowSorter(sorter);
				
				
				JScrollPane js = new JScrollPane();
				js.setViewportView(childTable);
				
				tabbedPane.add(t.getName(),js);
			}	        		
		}
		TableRowSorter<MyTableModel> parentSorter= new TableRowSorter<MyTableModel>(Singleton.getInstance().getMainFrame().getParrentModel());
		Singleton.getInstance().getMainFrame().getParrentTable().setRowSorter(parentSorter);

	}
}
