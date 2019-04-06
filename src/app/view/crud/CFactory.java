package app.view.crud;


import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import app.view.MyJTable;

import app.Singleton;
import app.model.ForeignKey;
import app.model.Table;
import app.model.attribute.Attribute;
import app.view.boolComponent.BoolComponent;
import app.view.boolComponent.CreateBoolComponent;
import app.view.charComponent.CharComponent;
import app.view.charComponent.CreateCharComponent;
import app.view.dateComponent.CreateDateComponent;
import app.view.dateComponent.DateComponent;
import app.view.intComponent.CreateIntComponent;
import app.view.intComponent.IntComponent;
import app.view.numberComponent.CreateNumberComponent;
import app.view.numberComponent.NumberComponent;
import app.view.stringComponent.CreateStringComponent;
import app.view.stringComponent.StringComponent;


public class CFactory extends CRUDAbstractFactory{

	
	public CFactory(ArrayList<Attribute> attributes) {
		super(attributes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public StringComponent createStringComponent(Attribute a) {
		
		return new CreateStringComponent(a);
	}

	@Override
	public NumberComponent createNumberComponent(Attribute a) {
		// TODO Auto-generated method stub
		return new CreateNumberComponent(a);
	}

	@Override
	public DateComponent createDateComponent(Attribute a) {
		// TODO Auto-generated method stub
		return new CreateDateComponent(a);
	}

	@Override
	public BoolComponent createBoolComponent(Attribute a) {
		// TODO Auto-generated method stub
		return new CreateBoolComponent(a);
	}
	
	

	@Override
	public JPanel generateComps() {
		JPanel ret=new JPanel();
		comps=new ArrayList<IValid>();
		BoxLayout bl=new BoxLayout(ret,BoxLayout.Y_AXIS);
		ret.setLayout(bl);
		ret.add(Box.createVerticalGlue());
		for(Attribute a : attributes){
			ret.add(Box.createVerticalStrut(10));
			switch(a.getType().getTypeName()){
			case "varchar":{
				CreateStringComponent c=(CreateStringComponent) createStringComponent(a);
				JPanel jp=CheckFK(a);
				if(jp==null) {
					ret.add(c);
				}else {
					fkVals.put(a.getCode(), c.getTf());
					jp.add(c);
				}
				comps.add(c);
				actionComps.put(a.getCode(),c.getTf());
				//System.out.println("dodao");
			}break;
			case "char":{
				CreateCharComponent c=(CreateCharComponent) createCharComponent(a);
				JPanel jp=CheckFK(a);
				if(jp==null) {
					ret.add(c);
				}else {
					fkVals.put(a.getCode(), c.getTf());
					jp.add(c);
				}
				comps.add(c);
				actionComps.put(a.getCode(),c.getTf());
				//System.out.println("dodao");
			}break;
			case "double":{
				CreateNumberComponent c=(CreateNumberComponent) createNumberComponent(a);
				JPanel jp=CheckFK(a);
				if(jp==null) {
					ret.add(c);
				}else {
					fkVals.put(a.getCode(), c.getTf());
					jp.add(c);
				}
				comps.add(c);
				actionComps.put(a.getCode(),c.getTf());
			}break;
			case "int":{
				CreateIntComponent c=(CreateIntComponent) createIntComponent(a);
				comps.add(c);
				JPanel jp=CheckFK(a);
				if(jp==null) {
					
					ret.add(c);
				}else {
					fkVals.put(a.getCode(), c.getTf());
					jp.add(c);
				}
				actionComps.put(a.getCode(),c.getTf());
			}break;
			case "date":{
				CreateDateComponent c=(CreateDateComponent) createDateComponent(a);
				comps.add(c);
				JPanel jp=CheckFK(a);
				if(jp==null) {
					
					ret.add(c);
				}else {
					fkVals.put(a.getCode(), c.getTf());
					jp.add(c);
				}
				actionComps.put(a.getCode(),c.getTf());
			}break;
			case "boolean":{
				CreateBoolComponent c=(CreateBoolComponent) createBoolComponent(a);
				comps.add(c);
				JPanel jp=CheckFK(a);
				if(jp==null) {
					ret.add(c);
				}else {
					fkVals.put(a.getCode(), c.getCb());
					jp.add(c);
				}
				actionComps.put(a.getCode(),c.getCb());
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
	public IntComponent createIntComponent(Attribute a) {
		// TODO Auto-generated method stub
		return new CreateIntComponent(a);
			
	}

	@Override
	public CharComponent createCharComponent(Attribute a) {
		// TODO Auto-generated method stub
		return new CreateCharComponent(a);
	}
	
	

}
