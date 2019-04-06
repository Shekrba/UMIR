package app.model.attribute;

public class Attribute {

	private String name;
	private String code;
	private AttributeType type;
	
	
	
	public Attribute(String name, String code, AttributeType type) {
		super();
		this.name = name;
		this.code = code;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public AttributeType getType() {
		return type;
	}
	public void setType(AttributeType type) {
		this.type = type;
	}
	
}
