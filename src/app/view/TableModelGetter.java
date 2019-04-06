package app.view;

import java.util.ArrayList;
import java.util.HashMap;

import app.Singleton;
import app.model.Table;
import app.model.attribute.Attribute;

/*
 * @author Igor Antoloviæ
 * Klasa sa statickim poljem mape svih table modela gde je kljuc tabela iz app.model paketa, odnosno naseg modela
 * */

public class TableModelGetter {
	private static HashMap<String, MyTableModel> tableMap = new HashMap<String, MyTableModel>();
	
	public static HashMap<String, MyTableModel> getTableMap() {
		return tableMap;
	}

	public static void setTableMap(HashMap<String, MyTableModel> tableMap) {
		TableModelGetter.tableMap = tableMap;
	}
/*
 * Vraca model iz mape ako on postoji, ako ne dodaje ga.
 * */
	public static MyTableModel getTableModel(Table t){
		if(tableMap.containsKey(t.getCode()))
			return tableMap.get(t.getCode());
		else{
			MyTableModel tm = new MyTableModel();
			tableMap.put(t.getCode(),tm);
			int x=0;
			for(Attribute a : t.getAttributes().values()){
    			tm.addColumn(a.getName());
    			tm.addColumName(a.getName());
    			if(a.getType().getTypeName().equals("boolean")) {
    				tm.addBool(x);
    			}
    			if(a.getType().getTypeName().equals("date")) {
    				tm.addDate(x);
    			}
    			
    			x++;
    		}
			return tm;
		}
	}
/*
 * Prilikom promene jezika menja imena kolona u tablemodel-u.
 * */
	public static void langChanged() {
		for(Table tab : Singleton.getInstance().getAllTables().values()){
			MyTableModel tbMd=getTableModel(tab);
			ArrayList<String> names=new ArrayList<String>();
			for(Attribute atr : tab.getAttributes().values()){
				names.add(atr.getName());
			}
			tbMd.setColumnNames(names);
		}
		
	}
}
