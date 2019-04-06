package app.model.attribute;

public class NumberType extends AttributeType {

	private int decimal;
	private Float preDef;

	public int getDecimal() {
		return decimal;
	}
	public void setDecimal(int decimal) {
		this.decimal = decimal;
	}
	public Float getPreDef() {
		return preDef;
	}
	public void setPreDef(Float preDef) {
		this.preDef = preDef;
	}
	
	public NumberType(String typeName,int decimal, Float preDef, boolean req, boolean unique) {
		super();
		this.typeName = typeName;
		this.decimal = decimal;
		this.preDef = preDef;
		this.req = req;
		this.unique = unique;
	}
	
	
	
}
