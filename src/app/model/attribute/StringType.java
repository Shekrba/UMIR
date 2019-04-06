package app.model.attribute;

public class StringType extends AttributeType{
	
	private int maxLength;
	private String preDef;
	
	public StringType(String typeName,int maxLength, String preDef, boolean req, boolean unique) {
		super();
		this.typeName = typeName;
		this.maxLength = maxLength;
		this.preDef = preDef;
		this.req = req;
		this.unique = unique;

	}
	public int getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	public String getPreDef() {
		return preDef;
	}
	public void setPreDef(String preDef) {
		this.preDef = preDef;
	}
	
	
	
}
