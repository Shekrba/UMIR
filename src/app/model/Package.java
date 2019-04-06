package app.model;

import java.util.ArrayList;

public class Package extends SchemaComponent{

	ArrayList<SchemaComponent> children;

	public Package() {
		children=new ArrayList<SchemaComponent>();
	}
	
	public Package(ArrayList<SchemaComponent> children) {
		super();
		this.children = children;
	}

	public void setChildren(ArrayList<SchemaComponent> children) {
		this.children = children;
	}

	public ArrayList<SchemaComponent> getChildren() {
		return children;
	}

	public void addChild(SchemaComponent comp) {
		
		children.add(comp);
		
	}
	
	
	
}
