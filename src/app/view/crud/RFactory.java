package app.view.crud;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import app.Singleton;
import app.model.attribute.Attribute;
import app.view.boolComponent.BoolComponent;
import app.view.boolComponent.ReadBoolComponent;
import app.view.boolComponent.UpdateBoolComponent;
import app.view.charComponent.CharComponent;
import app.view.charComponent.ReadCharComponent;
import app.view.dateComponent.DateComponent;
import app.view.dateComponent.ReadDateComponent;
import app.view.dateComponent.UpdateDateComponent;
import app.view.intComponent.IntComponent;
import app.view.intComponent.ReadIntComponent;
import app.view.numberComponent.NumberComponent;
import app.view.numberComponent.ReadNumberComponent;
import app.view.numberComponent.UpdateNumberComponent;
import app.view.stringComponent.ReadStringComponent;
import app.view.stringComponent.StringComponent;
import app.view.stringComponent.UpdateStringComponent;

public class RFactory extends CRUDAbstractFactory{

	protected ArrayList<JComponent> jcomponents;
	
	public RFactory(ArrayList<Attribute> attributes) {
		super(attributes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JPanel generateComps() {
		JPanel ret=new JPanel();
		jcomponents = new ArrayList<JComponent>();
		comps=new ArrayList<IValid>();
		BoxLayout bl=new BoxLayout(ret,BoxLayout.Y_AXIS);
		ret.setLayout(bl);
		ret.add(Box.createVerticalGlue());
		int i=-1;
		for(Attribute a : attributes){
			i++;
			ret.add(Box.createVerticalStrut(10));
			switch(a.getType().getTypeName()){
			case "varchar":{
				ReadStringComponent c=new ReadStringComponent(a);
				JPanel jp=CheckFK(a);
				if(jp==null) {
					ret.add(c);
				}else {
					fkVals.put(a.getCode(), c.getTf());
					jp.add(c);
				}
				comps.add(c);
				jcomponents.add(c.getTf());
				//System.out.println("dodao");
			}break;
			case "char":{
				ReadCharComponent c=new ReadCharComponent(a);
				JPanel jp=CheckFK(a);
				if(jp==null) {
					ret.add(c);
				}else {
					fkVals.put(a.getCode(), c.getTf());
					jp.add(c);
				}
				comps.add(c);
				jcomponents.add(c.getTf());
				//System.out.println("dodao");
			}break;
			case "double":{
				ReadNumberComponent c=new ReadNumberComponent(a);
				comps.add(c);
				JPanel jp=CheckFK(a);
				if(jp==null) {
					ret.add(c);
				}else {
					fkVals.put(a.getCode(), c.getTf());
					jp.add(c);
				}
				jcomponents.add(c.getTf());
				jcomponents.add(c.getTfTo());
			}break;
			case "int":{
				ReadIntComponent c=new ReadIntComponent(a);
				comps.add(c);
				JPanel jp=CheckFK(a);
				if(jp==null) {
					ret.add(c);
				}else {
					fkVals.put(a.getCode(), c.getTf());
					jp.add(c);
				}
				jcomponents.add(c.getTf());
				jcomponents.add(c.getTfTo());
			}break;
			case "date":{
				DateFormat formater=new SimpleDateFormat(Singleton.getInstance().getResourceBundle().getString("dateFormat"));
				ReadDateComponent c=null;
				
				c = new ReadDateComponent(a);
				comps.add(c);
				JPanel jp=CheckFK(a);
				if(jp==null) {
					ret.add(c);
				}else {
					fkVals.put(a.getCode(), c.getTf());
					jp.add(c);
				}
				jcomponents.add(c.getTf());
				jcomponents.add(c.getTf2());
			}break;
			case "boolean":{
				ReadBoolComponent c=new ReadBoolComponent(a);
				comps.add(c);
				JPanel jp=CheckFK(a);
				if(jp==null) {
					ret.add(c);
				}else {
					fkVals.put(a.getCode(), c.getCb());
					jp.add(c);
				}
				jcomponents.add(c.getCb());
				jcomponents.add(c.getFilter());
			}break;
			}
		}
		ret.add(Box.createVerticalStrut(10));
		for(JPanel jpl : fkComps.values()) {
			JPanel p=(JPanel) jpl.getComponent(1);
			p.add(Box.createVerticalStrut(10));
			ret.add(jpl);
		}
		
		ret.add(Box.createVerticalGlue());
		return ret;

	}

	@Override
	public StringComponent createStringComponent(Attribute a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NumberComponent createNumberComponent(Attribute a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DateComponent createDateComponent(Attribute a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoolComponent createBoolComponent(Attribute a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntComponent createIntComponent(Attribute a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CharComponent createCharComponent(Attribute a) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<JComponent> getJcomponents() {
		return jcomponents;
	}

	public void setJcomponents(ArrayList<JComponent> jcomponents) {
		this.jcomponents = jcomponents;
	}
	

}
