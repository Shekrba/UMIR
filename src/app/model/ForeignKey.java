package app.model;

import java.util.ArrayList;

public class ForeignKey {
	
	private ArrayList<String> referencinAttr;
	private String dependecyName;
	private String referencedTable;
	private ArrayList<String> referencedAttr;
	private String dependencyCode;
	
	
	public String getDependecyName() {
		return dependecyName;
	}
	public void setDependecyName(String dependecyName) {
		this.dependecyName = dependecyName;
	}
	
	public ArrayList<String> getReferencinAttr() {
		return referencinAttr;
	}
	public void setReferencinAttr(ArrayList<String> referencinAttr) {
		this.referencinAttr = referencinAttr;
	}
	public ArrayList<String> getReferencedAttr() {
		return referencedAttr;
	}
	public void setReferencedAttr(ArrayList<String> referencedAttr) {
		this.referencedAttr = referencedAttr;
	}
	public String getReferencedTable() {
		return referencedTable;
	}
	public void setReferencedTable(String referencedTable) {
		this.referencedTable = referencedTable;
	}
	
	public ForeignKey(ArrayList<String> referencinAttr, String dependecyName, String referencedTable,
			ArrayList<String> referencedAttr, String dependencyCode) {
		super();
		this.referencinAttr = referencinAttr;
		this.dependecyName = dependecyName;
		this.referencedTable = referencedTable;
		this.referencedAttr = referencedAttr;
		this.dependencyCode = dependencyCode;
	}
	public String getDependencyCode() {
		return dependencyCode;
	}
	public void setDependencyCode(String dependencyCode) {
		this.dependencyCode = dependencyCode;
	}
	
	
	
}
