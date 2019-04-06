package schema.parsers;

import java.util.ArrayList;
import java.util.HashMap;

import app.model.ForeignKey;
import app.model.attribute.Attribute;
/*
 * @author Igor Antoloviæ
 * Klasa se koristi za kreiranje tabele nakon prvog prolaza kroz stablo tokom parsiranja
 * */

public class FirstPassTable {

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String code;
	
	private HashMap<String,Attribute> attributes;
	
	private ArrayList<String> primaryKey;
	
	private ArrayList<ForeignKey> foreignKeys;

	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	

	public HashMap<String, Attribute> getAttributes() {
		return attributes;
	}


	public void setAttributes(HashMap<String, Attribute> attributes) {
		this.attributes = attributes;
	}


	

	


	

	public FirstPassTable(String name, String code, HashMap<String, Attribute> attributes, ArrayList<String> primaryKey,
			ArrayList<ForeignKey> foreignKeys) {
		super();
		this.name = name;
		this.code = code;
		this.attributes = attributes;
		this.primaryKey = primaryKey;
		this.foreignKeys = foreignKeys;
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
