package app.model;

import java.util.ArrayList;
import java.util.HashMap;

import app.model.attribute.Attribute;
import schema.parsers.FirstPassTable;

public class Table extends SchemaComponent{

	private String code;
	
	private ArrayList<Table> parent;
	
	private HashMap<String,String> parrentAttrs;
	
	private HashMap<String,Attribute> attributes;
	
	private ArrayList<String> primaryKey;
	
	private ArrayList<ForeignKey> foreignKeys;


	private ArrayList<Table> children;

	
	
	public ArrayList<Table> getChildren() {
		return children;
	}



	public void setChildren(ArrayList<Table> children) {
		this.children = children;
	}

	
	public void addChild(Table tc) {
		children.add(tc);
	}
	
	
	public Table(FirstPassTable fpt){
		code=fpt.getCode();
		attributes=fpt.getAttributes();
		primaryKey=fpt.getPrimaryKey();
		foreignKeys=fpt.getForeignKeys();
		name=fpt.getName();
		parent=new ArrayList<Table>();
		children=new ArrayList<Table>();
		parrentAttrs=new HashMap<String,String>();
	}
	
	public HashMap<String, String> getParrentAttrs() {
		return parrentAttrs;
	}



	public void setParrentAttrs(HashMap<String, String> parrentAttrs) {
		this.parrentAttrs = parrentAttrs;
	}



	public void open(){
		
	}
	
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	

	public ArrayList<Table> getParent() {
		return parent;
	}




	public void setParent(ArrayList<Table> parent) {
		this.parent = parent;
	}




	public Table(Table t) {
		super();
		this.code = t.getCode();
		this.parent = t.getParent();
		this.attributes = t.getAttributes();
		this.primaryKey = t.getPrimaryKey();
		this.foreignKeys = t.getForeignKeys();
		this.setName(t.getName());
	}

	public HashMap<String, Attribute> getAttributes() {
		return attributes;
	}


	public void setAttributes(HashMap<String, Attribute> attributes) {
		this.attributes = attributes;
	}


	public ArrayList<String> getPrimaryKey() {
		return primaryKey;
	}


	public void setPrimaryKey(ArrayList<String> primaryKey) {
		this.primaryKey = primaryKey;
	}


	public ArrayList<ForeignKey> getForeignKeys() {
		return foreignKeys;
	}

	public void setForeignKeys(ArrayList<ForeignKey> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}
	
	
}
