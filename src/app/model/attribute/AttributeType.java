package app.model.attribute;

public abstract class AttributeType {

	protected String typeName;
	protected boolean req;
	protected boolean unique;
	
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


	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	
	
}
