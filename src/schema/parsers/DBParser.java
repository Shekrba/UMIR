package schema.parsers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import app.Singleton;
import app.model.ForeignKey;
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
import app.view.MyTableModel;
import app.view.TableModelGetter;
import app.model.Package;

/**
 * @author Igor Antoloviæ
 * Klasa za parsiranje DB scheme
 * 
 */

public class DBParser implements Parser{

	boolean err;
	private HashMap<String,Table> allTables=new HashMap<String,Table>();
	private ArrayList<String> toRemove=new ArrayList<String>();
	
	
	/*
	 * Parsira string unosa i vraca null ako ima gresku a objekat seme ako je parsiranje uspesno
	 */
	@Override
	public Schema parse(Object o) {
		
		Schema sc=null;
		
		try {
			
			//Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim8-2","psw-2018-tim8-2","tim8-213636262");
			
			Connection conn=(Connection)o;
			
			
			
			
			String sql="SELECT * FROM PODSISTEM";
			
			Statement stmtPackage=conn.createStatement();
			ResultSet rsetPackage = stmtPackage.executeQuery(sql);
			
			HashMap<String,Package> packages=new HashMap<String,Package>();
			while(rsetPackage.next()) {
				String kodPackage=rsetPackage.getString("PO_OZNAKA");
				String nazivPackage=rsetPackage.getString("PO_NAZIV");
				Package p=new Package();
				p.setName(nazivPackage);
				packages.put(kodPackage, p);
			}
			
			sc=new Schema("PSW2018",packages.get("PSW2018"),Singleton.getInstance().getSchemaDialog().getTp().getText());
			
			sql="SELECT * FROM STRUKTURA_PODSISTEMA";
			
			Statement stmtPackgStruct=conn.createStatement();
			ResultSet rsetPackagStruct = stmtPackgStruct.executeQuery(sql);
			
			while(rsetPackagStruct.next()) {
				String parent=rsetPackagStruct.getString("PO_OZNAKA");
				String child=rsetPackagStruct.getString("POD_PO_OZNAKA");
				
				packages.get(parent).addChild(packages.get(child));
			}
			
			
			sql="SELECT * FROM TABELE";
			
			Statement stmtTable=conn.createStatement();
			ResultSet rsetTable = stmtTable.executeQuery(sql);
			
			while(rsetTable.next()) {
				String nazivTable=rsetTable.getString("TAB_NAZIV");
				String kodTable=rsetTable.getString("TAB_KOD");
				String kodPackage=rsetTable.getString("PO_OZNAKA");
				
				sql="SELECT * FROM ATRIBUTI WHERE TAB_KOD='"+kodTable+"'";
				
				Statement stmtAttr=conn.createStatement();
				ResultSet rsetAttr = stmtAttr.executeQuery(sql);
				HashMap<String,Attribute> attributes=new HashMap<String,Attribute>();
				ArrayList<String> pKeys=new ArrayList<String>();
				while(rsetAttr.next()) {
					Attribute a=null;
					String nazivAttr=rsetAttr.getString("ATR_LABELA");
					String kodAttr=rsetAttr.getString("ATR_KOD");
					String typeName=rsetAttr.getString("ATR_TIP");
					
					
					switch(typeName) {
					case "varchar":{
						int maxLength=rsetAttr.getInt("ATR_DUZINA");
						boolean req=false;
						req=rsetAttr.getBoolean("ATR_OBAVEZNO");
						StringType st=new StringType(typeName, maxLength, null, req, false);
						a=new Attribute(nazivAttr,kodAttr,st);
					}break;
					case "char":{
						int maxLength=rsetAttr.getInt("ATR_DUZINA");
						boolean req=false;
						req=rsetAttr.getBoolean("ATR_OBAVEZNO");
						CharType st=new CharType(typeName, maxLength, null, req, false);
						a=new Attribute(nazivAttr,kodAttr,st);
					}break;
					case "decimal":{
						int prec=rsetAttr.getInt("ATR_PRECIZNOST");
						boolean req=false;
						req=rsetAttr.getBoolean("ATR_OBAVEZNO");
						NumberType st=new NumberType("double", prec, null, req, false);
						a=new Attribute(nazivAttr,kodAttr,st);
					}break;
					case "int":{
						boolean req=false;
						req=rsetAttr.getBoolean("ATR_OBAVEZNO");
						IntType nt=new IntType(typeName,0,req,false);
						a=new Attribute(nazivAttr,kodAttr,nt);
					}break;
					case "bit":{
						BoolType nt=new BoolType("boolean",false);
						a=new Attribute(nazivAttr,kodAttr,nt);
					}break;
					case "datetime":{
						boolean req=false;
						req=rsetAttr.getBoolean("ATR_OBAVEZNO");
						DateType nt=new DateType("date",null,req,false);
						a=new Attribute(nazivAttr,kodAttr,nt);
					}break;
					}
					
					
					if(rsetAttr.getBoolean("ATR_DEO_PK")) {
						pKeys.add(a.getCode());
					}

					attributes.put(a.getCode(),a);
				}
				FirstPassTable fpt=new FirstPassTable(nazivTable,kodTable,attributes,pKeys,new ArrayList<ForeignKey>());
				Table t=new Table(fpt);
				packages.get(kodPackage).addChild(t);
				allTables.put(t.getCode(), t);
			}
			
			
			sql="SELECT * FROM STRANI_KLJUC";
			
			Statement stmtFK=conn.createStatement();
			ResultSet rsetFK = stmtFK.executeQuery(sql);
			
			while(rsetFK.next()) {
				String table=rsetFK.getString("TAB_TAB_KOD");
				String referencedTable=rsetFK.getString("TAB_KOD");
				String fkKey=rsetFK.getString("SK_KOD");
				String fkNaziv=rsetFK.getString("SK_LABELA");
				Table t=allTables.get(table);
				
				sql="SELECT * FROM KOLONE_U_STRANOM_KLJUCU WHERE SK_KOD='"+fkKey+"'";
				
				Statement stmtFKAttr=conn.createStatement();
				ResultSet rsetFKAttr = stmtFKAttr.executeQuery(sql);
				ArrayList<String> referencingAttrs=new ArrayList<String>();
				ArrayList<String> referencedAttrs=new ArrayList<String>();
				while(rsetFKAttr.next()) {
					String referencingAttr=rsetFKAttr.getString("ATR_ATR_KOD");
					String referencedAttr=rsetFKAttr.getString("ATR_KOD");
					referencingAttrs.add(referencingAttr);
					referencedAttrs.add(referencedAttr);
				}
				t.getForeignKeys().add(new ForeignKey(referencingAttrs,fkNaziv,referencedTable,referencedAttrs,fkKey));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		checkParserErrors();
		if(err) {
			return null;
		}
		findDeps(sc.getRootPackage());
		removeUnec(sc.getRootPackage());
		
		
		return sc;
	}

	
	/*
	 * Proverava ima li gresaka u parsiranju nakon incijalizacije pocetnih tabela
	 * Greske su tipa nepstojece polje u referenciranoj tabeli ili polje nije deo kljuca
	 * */
	public void checkParserErrors() {
		for(Table t : allTables.values()) {
			for(ForeignKey fk : t.getForeignKeys()) {
				for(String ringAttr : fk.getReferencinAttr()) {
					if(!(t.getAttributes().containsKey(ringAttr))) {
						System.out.println(t.getAttributes().toString());
						System.out.println(ringAttr);
						err=true;
						//parsingError
					}
				}
				Table redTable=allTables.get(fk.getReferencedTable());
				/*if(redTable==t) {
					System.out.println(t.getName());
					err=true;
				}*/
				for(String redAttr : fk.getReferencedAttr()) {
					if(!(redTable.getPrimaryKey().contains(redAttr))) {
						System.out.println(redTable.getPrimaryKey().toString());
						System.out.println(redAttr);
						err=true;
					}
				}
				
			}
		}
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
	
}
