package app.model;

public class Schema {

	private String name;
	private Package rootPackage;
	private String metaCode;
	
	public String getMetaCode() {
		return metaCode;
	}

	public void setMetaCode(String metaCode) {
		this.metaCode = metaCode;
	}

	public Schema(String name, Package rootPackage,String metaCode) {
		super();
		this.name = name;
		this.rootPackage = rootPackage;
		this.metaCode=metaCode;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Package getRootPackage() {
		return rootPackage;
	}
	public void setRootPackage(Package rootPackage) {
		this.rootPackage = rootPackage;
	}
	
	
	
}
