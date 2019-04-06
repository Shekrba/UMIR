package app.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import app.view.MyJTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import app.Singleton;
import app.model.Package;
import app.model.SchemaComponent;
import app.model.Table;
import app.model.attribute.Attribute;



/**
 * @author Milan Skrbic
 * 
 * Klasa u kojoj se inicijalizuje JTree. U initTree metodi na dvoklik nekog od cvorova klasa ubacuje
 * u desni deo aplikacije selektovanu tabelu i ispod nje njene childove ako ih ima
 *
 */
public class TreePanel extends JScrollPane {
	private JTree tree;
	   
	private DefaultTreeModel treeModel;
	   
	public TreePanel(){
		super();
		initTree();
		
	}
	
	public void initTree() {
		
		Singleton.getInstance().setAllTables(new HashMap<String, Table>());
		Package rootPackage = Singleton.getInstance().getSchema().getRootPackage();
		
		
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootPackage);
		treeModel = new DefaultTreeModel(rootNode);
		int x=-1;
		if(tree!=null)
			if(tree.getSelectionRows().length!=0)
				x=tree.getSelectionRows()[0];
		tree = new JTree(treeModel);
		tree.setCellRenderer(new TreeRenderer());
		
		addNodes(rootNode, rootPackage);
		
		treeModel.reload();
		this.setViewportView(tree);
		
		tree.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
		        int selRow = tree.getRowForLocation(e.getX(), e.getY());
		        TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
		        tree.expandPath(selPath);
		        if(selRow != -1) {
		            if(e.getClickCount() == 2) {
		           	
		            	DefaultMutableTreeNode sel=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		            	Object o = sel.getUserObject();
		            	if(o instanceof Table){
		            		Table table = (Table) o;
		            		
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
		});
		expandAllNodes(tree, 0, tree.getRowCount());
	}
	
	private void expandAllNodes(JTree tree, int startingIndex, int rowCount){
	    for(int i=startingIndex;i<rowCount;++i){
	        tree.expandRow(i);
	    }

	    if(tree.getRowCount()!=rowCount){
	        expandAllNodes(tree, rowCount, tree.getRowCount());
	    }
	}
	
	private void addNodes(DefaultMutableTreeNode parentNode, SchemaComponent sc) {
		ResourceBundle rb=Singleton.getInstance().getResourceBundle();
		DefaultMutableTreeNode node;

	    if(sc instanceof Package){
	    	Package p = (Package) sc;
	    	String name;
	    	try{
	    		name=rb.getString(p.getName());
	    	}catch( java.util.MissingResourceException e){
	    		name=null;
	    	}
    		if(name!=null)
    			p.setName(name);
	    	Iterator<SchemaComponent> iterator = p.getChildren().iterator();
            while (iterator.hasNext()) {
            	SchemaComponent newComponent = iterator.next();
                node = new DefaultMutableTreeNode(newComponent);
                parentNode.add(node);
                addNodes(node, newComponent);
            }
	    }
	    else if(sc instanceof Table && !((Table) sc).getChildren().isEmpty()){
	    	Table ct = (Table) sc;
	    	String name;
	    	try{
	    		name=rb.getString(ct.getCode());
	    	}catch( java.util.MissingResourceException e){
	    		name=null;
	    	}
    		if(name!=null)
    			ct.setName(name);
    		for (Attribute a : ct.getAttributes().values()) {
    			String nameA;
    			try{
    				nameA=rb.getString(a.getCode());
    			}catch( java.util.MissingResourceException e){
    				nameA=null;
    			}
        		if(nameA!=null)
        			a.setName(nameA);
			}
	    	Iterator<Table> iterator = ct.getChildren().iterator();
            while (iterator.hasNext()) {
            	Table newComponent = iterator.next();
                node = new DefaultMutableTreeNode(newComponent);
                parentNode.add(node);
                addNodes(node, newComponent);
            }
	    }
	    
	    if(sc instanceof Table) {
	    	Table t = (Table) sc;
	    	String name;
	    	try{
	    		name=rb.getString(t.getCode());
	    	}catch( java.util.MissingResourceException e){
	    		name=null;
	    	}
    		if(name!=null)
    			t.setName(name);
    		for (Attribute a : t.getAttributes().values()) {
    			String nameA;
    			try{
    				nameA=rb.getString(a.getCode());
    			}catch( java.util.MissingResourceException e){
    				nameA=null;
    			}
        		if(nameA!=null)
        			a.setName(nameA);
			}
	    	Singleton.getInstance().getAllTables().put(t.getCode(),t);
	    }
	}
}
