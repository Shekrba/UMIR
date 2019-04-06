package app.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import app.view.MyJTable;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import de.javasoft.synthetica.dark.SyntheticaDarkLookAndFeel;
import schema.view.SchemaMenuBar;
import schema.view.SchemaToolBar;

import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import app.Singleton;
import app.model.Table;
import app.model.attribute.Attribute;


public class MainFrame extends JFrame {
	
	private JSplitPane center;
	private JSplitPane right;
	private MyJTable parrentTable;
	private MyJTable childTable;
	private JScrollPane parrentScroll;
	private JTabbedPane tabbedPane;
	private JTabbedPane parrentName;
	private Table showingParrent;
	private ToolBar toolbar;
	
	
	public TreePanel getTree() {
		return tree;
	}

	public void setTree(TreePanel tree) {
		this.tree = tree;
	}

	private TreePanel tree;
	private MyTableModel parrentModel;
	private MyTableModel childModel;
	
	public MainFrame() {
		super();

		try
		{
		  UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
		  UIManager.put("Synthetica.tabbedPane.keepOpacity", true);

		}
		catch (Exception e)
		{
		  e.printStackTrace();
		}
		
		
		setIconImage(new ImageIcon("images/eyeofra.jpg").getImage());
		
		this.setTitle("UMIR");
		this.setSize(1100, 735);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	    init();
	    
	    
				
		
	}

	public void init(){
		
		if(center!=null){
			this.remove(center);
			
		}
		if(Singleton.getInstance().getSchemaDialog() != null){
		    Singleton.getInstance().getSchemaDialog().setJMenuBar(new SchemaMenuBar());
		    
		    SchemaToolBar stb = new SchemaToolBar();		    
		    Singleton.getInstance().getSchemaDialog().remove(Singleton.getInstance().getSchemaDialog().getToolbar());
		    Singleton.getInstance().getSchemaDialog().setToolbar(stb);
		    Singleton.getInstance().getSchemaDialog().add(stb,BorderLayout.NORTH);
		    
		    		    		    
		    Singleton.getInstance().getSchemaDialog().getOkButton().setText(Singleton.getInstance().getResourceBundle().getString("confirm"));
		    
		    Singleton.getInstance().getSchemaDialog().setTitle(Singleton.getInstance().getResourceBundle().getString("metaschema editor"));
		}
		MenuBar mb=new MenuBar();
		this.setJMenuBar(mb);
		
		
		
		if(toolbar != null){
			this.remove(toolbar);
		}
		
		toolbar = new ToolBar();
		
		TableToolBar tableToolbar = new TableToolBar();
	    add(toolbar, BorderLayout.NORTH);
	    
	    tree = new TreePanel();
	    if(right!=null){
	    	TableModelGetter.langChanged();
	    }
	    int selRowTable=-1;
	    if(parrentTable!=null)
	    	selRowTable=parrentTable.getSelectedRow();
	    
	    
	    right = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		right.setDividerLocation(300);
	    
	    JPanel tableToolBarPanel = new JPanel();
		tableToolBarPanel.add(tableToolbar);
		
		tableToolBarPanel.add(right);
	    
	    center=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	    center.setDividerLocation(250);
		center.setLeftComponent(tree);
		center.setRightComponent(tableToolBarPanel);	
	    	    
	    parrentScroll = new JScrollPane();
	    int x=0;
	    if(tabbedPane!=null)
	    	x=tabbedPane.getSelectedIndex();
	   
	    tabbedPane = new JTabbedPane();
	    parrentName = new JTabbedPane();
	    
	    if(parrentModel==null){
	    	if(showingParrent!=null)
	    		parrentModel = TableModelGetter.getTableModel(showingParrent);
	    	else
	    		parrentModel=new MyTableModel();
	    }
	    
	    parrentTable = MyJTable.create(parrentModel);
	    
	    parrentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
	    parrentTable.setPreferredScrollableViewportSize(new Dimension(800, 600));
	    parrentTable.setFillsViewportHeight(true);
	    
	    parrentScroll.setViewportView((parrentTable));
	    
	    if(showingParrent!=null){
	    	parrentName.add(showingParrent.getName(),parrentScroll);
	    	for(Table tbl : showingParrent.getChildren()){
	    		JScrollPane js = new JScrollPane();
    			js.setViewportView(MyJTable.create(TableModelGetter.getTableModel(tbl)));
	    		tabbedPane.add(tbl.getName(), js);
	    	}
	    	if(!showingParrent.getChildren().isEmpty())
	    		tabbedPane.setSelectedIndex(x);
	    	
	    }
	    else
	    	parrentName.add(parrentScroll);
	    
	    parrentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if(parrentTable.getSelectedRow()!=-1 && !showingParrent.getChildren().isEmpty()){
					
					java.util.List<RowFilter<MyTableModel,Object>> filters = new ArrayList<RowFilter<MyTableModel,Object>>();
				    //If current expression doesn't parse, don't update.
					for(String s : showingParrent.getPrimaryKey()){
						Object o= parrentModel.getValueAt(parrentTable.getSelectedRow(), parrentModel.getIndexByName(showingParrent.getAttributes().get(s).getName()));
						String x;
						if(o instanceof Integer){
							x=Integer.toString((Integer)o);
							String keyVal=x;
							Table child=showingParrent.getChildren().get(tabbedPane.getSelectedIndex());
							childModel=TableModelGetter.getTableModel(child);
							Integer x1=childModel.getIndexByName(child.getAttributes().get(child.getParrentAttrs().get(s)).getName());
					        filters.add(RowFilter.regexFilter(keyVal, x1)); 
						}else if (o instanceof Date){
							DateFormat format1 = new SimpleDateFormat(Singleton.getInstance().getResourceBundle().getString("dateFormat"));
							Date date=(Date)o;
							
							Table child=showingParrent.getChildren().get(tabbedPane.getSelectedIndex());
							childModel=TableModelGetter.getTableModel(child);
							Integer x1=childModel.getIndexByName(child.getAttributes().get(child.getParrentAttrs().get(s)).getName());
					        //filters.add(RowFilter.regexFilter(keyVal, x1)); 
							filters.add(RowFilter.dateFilter(ComparisonType.EQUAL, date,x1));
						}else{
							x=(String)o;
							String keyVal=x;
							Table child=showingParrent.getChildren().get(tabbedPane.getSelectedIndex());
							childModel=TableModelGetter.getTableModel(child);
							Integer x1=childModel.getIndexByName(child.getAttributes().get(child.getParrentAttrs().get(s)).getName());
					        filters.add(RowFilter.regexFilter(keyVal, x1)); 
						}
						
					}
					RowFilter<MyTableModel,Object> rf=RowFilter.andFilter(filters);
					childTable=(MyJTable)((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView();
					TableRowSorter<MyTableModel> sorter=new TableRowSorter<MyTableModel>((MyTableModel)childTable.getModel());
					
					if(sorter!=null){
						sorter.setRowFilter(rf);
						childTable.setRowSorter(sorter);
					}
				}
			}
		
		});
	    
	    if(selRowTable!=-1)
    		parrentTable.setRowSelectionInterval(selRowTable, selRowTable);
	    
	    childModel = new MyTableModel();		
		childTable = MyJTable.create(childModel);		
		/*childTable.setPreferredScrollableViewportSize(new Dimension(900, 600));
		childTable.setFillsViewportHeight(true);*/
		
		
		right.setTopComponent(parrentName);
		right.setBottomComponent(tabbedPane);
		
	 	
		
		this.add(center);
		this.repaint();
		this.revalidate();
	}

	
	public JSplitPane getCenter() {
		return center;
	}

	public void setCenter(JSplitPane center) {
		this.center = center;
	}

	public JSplitPane getRight() {
		return right;
	}

	public void setRight(JSplitPane right) {
		this.right = right;
	}

	public MyJTable getParrentTable() {
		return parrentTable;
	}

	public void setParrentTable(MyJTable parrentTable) {
		this.parrentTable = parrentTable;
	}

	public MyJTable getChildTable() {
		return childTable;
	}	

	public void setChildTable(MyJTable childTable) {
		this.childTable = childTable;
	}

	public MyTableModel getParrentModel() {
		return parrentModel;
	}

	public void setParrentModel(MyTableModel parrentModel) {
		this.parrentModel = parrentModel;
	}

	public MyTableModel getChildModel() {
		return childModel;
	}

	public void setChildModel(MyTableModel childModel) {
		this.childModel = childModel;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public JScrollPane getParrentScroll() {
		return parrentScroll;
	}

	public void setParrentScroll(JScrollPane parrentScroll) {
		this.parrentScroll = parrentScroll;
	}

	public JTabbedPane getParrentName() {
		return parrentName;
	}

	public void setParrentName(JTabbedPane parrentName) {
		this.parrentName = parrentName;
	}

	public Table getShowingParrent() {
		return showingParrent;
	}

	public void setShowingParrent(Table showingParrent) {
		this.showingParrent = showingParrent;
	}	
	
}
