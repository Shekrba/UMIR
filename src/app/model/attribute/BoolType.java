package app.model.attribute;

public class BoolType extends AttributeType {
	
	private Boolean preDef;

	public Boolean isPreDef() {
		return preDef;
	}

	public void setPreDef(Boolean preDef) {
		this.preDef = preDef;
	}

	public BoolType(String typeName,Boolean preDef) {
		super();
		this.typeName = typeName;
		this.preDef = preDef;
	} 
	

}
