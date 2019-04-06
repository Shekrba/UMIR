package app.view.crud;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import app.Singleton;
import app.model.Table;
import app.model.attribute.Attribute;
import app.view.boolComponent.BoolComponent;
import app.view.boolComponent.CreateBoolComponent;
import app.view.boolComponent.UpdateBoolComponent;
import app.view.charComponent.CharComponent;
import app.view.charComponent.UpdateCharComponent;
import app.view.dateComponent.CreateDateComponent;
import app.view.dateComponent.DateComponent;
import app.view.dateComponent.UpdateDateComponent;
import app.view.intComponent.IntComponent;
import app.view.intComponent.UpdateIntComponent;
import app.view.numberComponent.CreateNumberComponent;
import app.view.numberComponent.NumberComponent;
import app.view.numberComponent.UpdateNumberComponent;
import app.view.stringComponent.CreateStringComponent;
import app.view.stringComponent.StringComponent;
import app.view.stringComponent.UpdateStringComponent;

public class UFactory extends CRUDAbstractFactory{

	private ArrayList<Object> selectedRow;
	
	public UFactory(ArrayList<Attribute> attributes,ArrayList<Object> selectedRow) {
		super(attributes);
		this.selectedRow=selectedRow;
		// TODO Auto-generated constructor stub
	}

	@Override
	public JPanel generateComps() {
		JPanel ret=new JPanel();
		comps=new ArrayList<IValid>();
		BoxLayout bl=new BoxLayout(ret,BoxLayout.Y_AXIS);
		ret.setLayout(bl);
		ret.add(Box.createVerticalGlue());
		Table table = Singleton.getInstance().getMainFrame().getShowingParrent();
		int i=-1;
		for(Attribute a : attributes){
			i++;
			ret.add(Box.createVerticalStrut(10));
			switch(a.getType().getTypeName()){
			case "varchar":{
				UpdateStringComponent c=new UpdateStringComponent(a,(String)selectedRow.get(i));
				JPanel jp=CheckFK(a);
				if(jp==null) {
					ret.add(c);
				}else {
					if(table.getPrimaryKey().contains(a.getCode())){
						zoom.setEnabled(false);
					}
					
					fkVals.put(a.getCode(), c.getTf());
					jp.add(c);
				}
				comps.add(c);
				actionComps.put(a.getCode(),c.getTf());
				//System.out.println("dodao");
			}break;
			case "char":{
				UpdateCharComponent c=new UpdateCharComponent(a,(String)selectedRow.get(i));
				JPanel jp=CheckFK(a);
				if(jp==null) {
					ret.add(c);
				}else {
					if(table.getPrimaryKey().contains(a.getCode())){
						zoom.setEnabled(false);
					}
					
					fkVals.put(a.getCode(), c.getTf());
					jp.add(c);
				}
				comps.add(c);
				actionComps.put(a.getCode(),c.getTf());
				//System.out.println("dodao");
			}break;
			case "double":{
				Float f = ((BigDecimal)selectedRow.get(i)).setScale(2, RoundingMode.DOWN).floatValue();
				UpdateNumberComponent c=new UpdateNumberComponent(a,f);
				JPanel jp=CheckFK(a);
				if(jp==null) {
					ret.add(c);
				}else {
					if(table.getPrimaryKey().contains(a.getCode())){
						zoom.setEnabled(false);
					}
					
					fkVals.put(a.getCode(), c.getTf());
					jp.add(c);
				}
				comps.add(c);
				actionComps.put(a.getCode(),c.getTf());
			}break;
			case "int":{
				UpdateIntComponent c=new UpdateIntComponent(a,(int)selectedRow.get(i));
				JPanel jp=CheckFK(a);
				if(jp==null) {
					ret.add(c);
				}else {
					if(table.getPrimaryKey().contains(a.getCode())){
						zoom.setEnabled(false);
					}
					
					fkVals.put(a.getCode(), c.getTf());
					jp.add(c);
				}
				comps.add(c);
				actionComps.put(a.getCode(),c.getTf());
			}break;
			case "date":{
				UpdateDateComponent c=null;
				
				c = new UpdateDateComponent(a,(Date)selectedRow.get(i));
				
				JPanel jp=CheckFK(a);
				if(jp==null) {
					ret.add(c);
				}else {
					if(table.getPrimaryKey().contains(a.getCode())){
						zoom.setEnabled(false);
					}
					
					fkVals.put(a.getCode(), c.getTf());
					jp.add(c);
				}
				comps.add(c);
				actionComps.put(a.getCode(),c.getTf());
			}break;
			case "boolean":{
				UpdateBoolComponent c=new UpdateBoolComponent(a,(boolean)selectedRow.get(i));
				JPanel jp=CheckFK(a);
				if(jp==null) {
					ret.add(c);
				}else {
					if(table.getPrimaryKey().contains(a.getCode())){
						zoom.setEnabled(false);
					}
					
					fkVals.put(a.getCode(), c.getCb());
					jp.add(c);
				}
				comps.add(c);
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

}
