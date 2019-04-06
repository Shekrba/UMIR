package schema.parsers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.tree.DefaultMutableTreeNode;

import org.json.JSONArray;
import org.json.JSONObject;

import app.Singleton;
import app.model.ForeignKey;
import app.model.Package;
import app.model.Schema;
import app.model.SchemaComponent;
import app.model.Table;
import app.model.attribute.Attribute;
import app.model.attribute.BoolType;
import app.model.attribute.CharType;
import app.model.attribute.DateType;
import app.model.attribute.IntType;
import app.model.attribute.NumberType;
import app.model.attribute.StringType;
import app.model.Table;

/**
 * @author Igor Antoloviæ
 * Klasa za parsiranje JSON scheme
 */
public class JSONParser implements Parser{

	private boolean err;
	
	public boolean getErr(){
		return err;
	}
	
	/*
	 * Parsira string unosa i vraca null ako ima gresku a objekat seme ako je parsiranje uspesno
	 */
	@Override
	public Schema parse(Object o) {
		
		JSONObject json=(JSONObject) o;
		String name=json.getString("name");
		JSONObject pcgO= json.getJSONObject("rootPackage");
		JSONArray pcg=pcgO.getJSONArray("children");
		Package p=new Package(hajhohajho(pcg));
		p.setName(pcgO.getString("packageName"));
		checkParserErrors();
		if(err) {
			return null;
		}
		findDeps(p);
		removeUnec(p);
		Schema ret=new Schema(name,p,json.toString(2));
		return ret;
		
	}
	
	private HashMap<String,Table> allTables=new HashMap<String,Table>();
	private ArrayList<String> toRemove=new ArrayList<String>();
	
	/*
	 * Proverava ima li gresaka u parsiranju nakon incijalizacije pocetnih tabela
	 * Greske su tipa nepstojece polje u referenciranoj tabeli ili polje nije deo kljuca
	 * */
	public void checkParserErrors() {
		for(Table t : allTables.values()) {
			for(ForeignKey fk : t.getForeignKeys()) {
				for(String ringAttr : fk.getReferencinAttr()) {
					if(!(t.getAttributes().containsKey(ringAttr))) {
						err=true;
						//parsingError
					}
				}
				Table redTable=allTables.get(fk.getReferencedTable());
				if(redTable==t) {
					err=true;
				}
				for(String redAttr : fk.getReferencedAttr()) {
					if(!(redTable.getPrimaryKey().contains(redAttr))) {
						err=true;
					}
				}
				
			}
		}
	}
	
	public boolean isErr() {
		return err;
	}

	public void setErr(boolean err) {
		this.err = err;
	}

	public HashMap<String, Table> getAllTables() {
		return allTables;
	}

	public void setAllTables(HashMap<String, Table> allTables) {
		this.allTables = allTables;
	}

	/*
	 * Trazi zavisnosti izmedju tabela, odnosno odnose Parrent Child
	 * 
	 */
	public void findDeps(SchemaComponent sc) {
		if(sc instanceof Package){
	    	Package p = (Package) sc;
	    	Iterator<SchemaComponent> iterator = p.getChildren().iterator();
            while (iterator.hasNext()) {
            	SchemaComponent newComponent = iterator.next();
                findDeps(newComponent);
            }
	    }
	    else{
	    	Table ct = (Table) sc;
	    	boolean flag=checkTable(ct);
	    	if(flag) {
	    		toRemove.add(ct.getName());
	    	}
	    }   	
	}
	
	 /*
		 * Brise child tabele iz stabla ukoliko imaju parrent tabelu
		 * */
	public void removeUnec(SchemaComponent sc) {
		if(sc instanceof Package){
	    	Package p = (Package) sc;
	    	Iterator<SchemaComponent> iterator = p.getChildren().iterator();
	    	ArrayList<Table> toAdd=new ArrayList<Table>();
            while (iterator.hasNext()) {
            	SchemaComponent newComponent = iterator.next();
            	if(newComponent instanceof Table) {
            		if(toRemove.contains(newComponent.getName())) {
            			iterator.remove();
            		}
            	}else {
            		removeUnec(newComponent);
            	}
            }
            p.getChildren().addAll(toAdd);
	    }	
	}
	
	
	
	
	/*
	 * Proverava da li je tabela za brisanje jer ima parrent table
	 * */
	private boolean checkTable(Table tab) {
		boolean ret=false;
		for(ForeignKey fk : tab.getForeignKeys()) {
			if((tab.getPrimaryKey().containsAll(fk.getReferencinAttr()))) {
				Table parentTbl=(Table) allTables.get(fk.getReferencedTable());
				parentTbl.addChild(tab);
				tab.getParent().add(parentTbl);
				for(int i=0; i<fk.getReferencedAttr().size() ; i++){
					tab.getParrentAttrs().put(fk.getReferencedAttr().get(i), fk.getReferencinAttr().get(i));
				}
				ret=true;
			}
		}
		return ret;
	}


	/*
	 * Rekurzivno kreira sve pakete i tabele, u prvom prolazu i dodaje ih u parrent pakete
	 * */
	public ArrayList<SchemaComponent> hajhohajho(JSONArray arr) {
		ArrayList<SchemaComponent> ret=new ArrayList<SchemaComponent>();
		HashMap<String,FirstPassTable> tables=new HashMap<String,FirstPassTable>();
		for (int i = 0; i < arr.length(); i++) {
			JSONObject obj=arr.getJSONObject(i);
			if(obj.has("packageName")) {
				JSONArray children=obj.getJSONArray("children");
				Package p=new Package(hajhohajho(children));
				
					p.setName(obj.getString("packageName"));
				
				ret.add(p);
			}else {
				
				String name=obj.getString("tableName");
				String code=obj.getString("code");
				
				JSONArray attrs=obj.getJSONArray("attributes");
				HashMap<String,Attribute> attributes=new HashMap<String,Attribute>();
				for(int j=0; j<attrs.length(); j++) {
					JSONObject attr=attrs.getJSONObject(j);
					
					String attrName=attr.getString("name");
					String attrCode=attr.getString("code");
					
					JSONObject attrType=attr.getJSONObject("type");
					String typeName=attrType.getString("typeName");
					Attribute a=null;
					switch(typeName) {
						case "varchar":{
							int maxLength=attrType.getInt("maxLength");
							String preDef=null;
							if(attrType.has("preDef")){
								preDef=attrType.getString("preDef");
							}
							boolean req=false;
							if(attrType.has("req")) {
								req=attrType.getBoolean("req");
							}
							boolean unique=false;
							if(attrType.has("unique")) {
								unique=attrType.getBoolean("unique");
							}
							StringType st=new StringType(typeName, maxLength, preDef, req, unique);
							a=new Attribute(attrName,attrCode,st);
						}break;
						case "char":{
							int maxLength=attrType.getInt("length");
							String preDef=null;
							if(attrType.has("preDef")){
								preDef=attrType.getString("preDef");
							}
							boolean req=false;
							if(attrType.has("req")) {
								req=attrType.getBoolean("req");
							}
							boolean unique=false;
							if(attrType.has("unique")) {
								unique=attrType.getBoolean("unique");
							}
							CharType st=new CharType(typeName, maxLength, preDef, req, unique);
							a=new Attribute(attrName,attrCode,st);
						}break;
						case "double":{
							int decimal=attrType.getInt("decimal");
							Float preDef=null;
							if(attrType.has("preDef")){
								preDef=attrType.getFloat("preDef");
							}
							boolean req=false;
							if(attrType.has("req")) {
								req=attrType.getBoolean("req");
							}
							boolean unique=false;
							if(attrType.has("unique")) {
								unique=attrType.getBoolean("unique");
							}
							NumberType nt=new NumberType(typeName,decimal,preDef,req,unique);
							a=new Attribute(attrName,attrCode,nt);
						}break;
						case "int":{
						
							Integer preDef=null;
							if(attrType.has("preDef")){
								preDef=attrType.getInt("preDef");
							}
							boolean req=false;
							if(attrType.has("req")) {
								req=attrType.getBoolean("req");
							}
							boolean unique=false;
							if(attrType.has("unique")) {
								unique=attrType.getBoolean("unique");
							}
							IntType nt=new IntType(typeName,preDef,req,unique);
							a=new Attribute(attrName,attrCode,nt);
						}break;
						case "boolean":{
							boolean preDef=false;
							if(attrType.has("preDef")){
								preDef=attrType.getBoolean("preDef");
							}
							BoolType bt=new BoolType(typeName,preDef);
							a=new Attribute(attrName,attrCode,bt);
						}break;
						case "date":{
							Date preDef=null;
							if(attrType.has("preDef")){
								DateFormat formater=new SimpleDateFormat("dd.MM.yyyy.");
								String preDefStr=attrType.getString("preDef");
								try {
									preDef=formater.parse(preDefStr);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							boolean req=false;
							if(attrType.has("req")) {
								req=attrType.getBoolean("req");
							}
							boolean unique=false;
							if(attrType.has("unique")) {
								unique=attrType.getBoolean("unique");
							}
							DateType dt=new DateType(typeName,preDef,req,unique);
							a=new Attribute(attrName,attrCode,dt);
						}break;
					
					}
					attributes.put(a.getCode(), a);
					
					
				}
				
				JSONArray prmKey=obj.getJSONArray("primaryKey");
				ArrayList<String> primaryKey=new ArrayList<String>();
				for(int j=0; j<prmKey.length(); j++) {
					String attrCode=prmKey.getString(j);
					if(attributes.get(attrCode)!=null) {
						primaryKey.add(attrCode);
						Attribute a=attributes.get(attrCode);
						a.getType().setReq(true);
					}else {
						err=true;
						//parsingError
					}
				}
				
				JSONArray frgKeys=obj.getJSONArray("foreignKeys");
				ArrayList<ForeignKey> foreignKeys=new ArrayList<ForeignKey>();
				for(int j=0; j<frgKeys.length(); j++) {
					JSONObject fkJSON=frgKeys.getJSONObject(j);
					String dpName=fkJSON.getString("dependencyName");
					String refTableCode=fkJSON.getString("referencedTable");
					
					JSONArray redAttrs=fkJSON.getJSONArray("referencedAttr");
					ArrayList<String> referencedAttr=new ArrayList<String>();
					for(int k=0; k<redAttrs.length(); k++) {
						String redAt=redAttrs.getString(k);
						referencedAttr.add(redAt);
					}
					
					JSONArray ringAttrs=fkJSON.getJSONArray("referencingAttr");
					ArrayList<String> referencingAttr=new ArrayList<String>();
					for(int k=0; k<ringAttrs.length(); k++) {
						String ringAt=ringAttrs.getString(k);
						referencingAttr.add(ringAt);
					}
					foreignKeys.add(new ForeignKey(referencingAttr,dpName,refTableCode,referencedAttr,dpName));
				}
				tables.put(code,new FirstPassTable(name,code,attributes,primaryKey,foreignKeys));
			}
			
		}
		
		for(FirstPassTable t : tables.values()) {
			Table ct=new Table(t);
			allTables.put(ct.getCode(), ct);
			ret.add(ct);
		}
		
		
		return ret;
	}

}
