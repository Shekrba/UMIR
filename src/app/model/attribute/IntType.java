package app.model.attribute;

public class IntType extends AttributeType {
	private Integer preDef;
	
	public Integer getPreDef() {
		return preDef;
	}
	public void setPreDef(Integer preDef) {
		this.preDef = preDef;
	}
	
	public IntType(String typeName, Integer preDef, boolean req, boolean unique) {
		super();
		this.typeName = typeName;
		this.preDef = preDef;
		this.req = req;
		this.unique = unique;
	}
}
