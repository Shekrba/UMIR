package app.view;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;


import app.Singleton;
import app.model.Table;
import app.model.Table;
import app.model.Package;
import app.model.SchemaComponent;

public class TreeRenderer extends DefaultTreeCellRenderer {
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		
		DefaultMutableTreeNode node=(DefaultMutableTreeNode) value;
		Object o=node.getUserObject();
		
		if (o instanceof Package){
			Package p=(Package)o;
			setText(p.getName());
			setIcon(new ImageIcon("images/package.png"));
		}
		else if (o instanceof Table){
			Table t=(Table) o;
			setText(t.getName());
			if(t.getChildren().isEmpty()) {
				setIcon(new ImageIcon("images/table.png"));
			}else {
				setIcon(new ImageIcon("images/complextable.png"));
			}
		}else if (o instanceof SchemaComponent){
			SchemaComponent sc=(SchemaComponent)o;
			setText(sc.getName());
			setIcon(new ImageIcon("images/table.png"));
		}
		return this;
	}
}
