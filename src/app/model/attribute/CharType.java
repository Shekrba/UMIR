package app.model.attribute;

public class CharType extends AttributeType{
	
	private int length;
	private String preDef;
	
	public CharType(String typeName,int length, String preDef, boolean req, boolean unique) {
		super();
		this.typeName = typeName;
		this.length = length;
		this.preDef = preDef;
		this.req = req;
		this.unique = unique;

	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getPreDef() {
		return preDef;
	}
	public void setPreDef(String preDef) {
		this.preDef = preDef;
	}
	
	
	
}
