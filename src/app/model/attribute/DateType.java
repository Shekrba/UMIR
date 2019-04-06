package app.model.attribute;

import java.util.Date;

public class DateType extends AttributeType{

	private Date preDef;
	private boolean req;
	private boolean unique;
	
	
	
	public DateType(String typeName,Date preDef, boolean req, boolean unique) {
		super();
		this.typeName=typeName;
		this.preDef = preDef;
		this.req = req;
		this.unique = unique;
	}
	public Date getPreDef() {
		return preDef;
	}
	public void setPreDef(Date preDef) {
		this.preDef = preDef;
	}
	public boolean isReq() {
		return req;
	}
	public void setReq(boolean req) {
		this.req = req;
	}
	public boolean isUnique() {
		return unique;
	}
	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	
	
	
}
