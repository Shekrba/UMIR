package app.view.crud;

import java.util.ArrayList;

import app.Singleton;
import app.model.attribute.Attribute;

public class CRUDFactoryMaker {

	private static CRUDAbstractFactory factory=null;
	
	public static CRUDAbstractFactory getFactory(String choice,ArrayList<Attribute> attrs){
		if(choice.equals("CREATE")){
			factory=new CFactory(attrs);
		}
		if(choice.equals("UPDATE")){
			//factory=new UFactory(attrs,o);
			factory=new UFactory(attrs,Singleton.getInstance().getMainFrame().getParrentModel().getValueAtRow(Singleton.getInstance().getMainFrame().getParrentTable().getSelectedRow()));
		}
		if(choice.equals("READ")){
			//factory=new UFactory(attrs,o);
			factory=new RFactory(attrs);
		}
		return factory;
	}
	
}
