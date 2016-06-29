package cl.ubb.agil.model;

public class CustomerCategory {
	
	private int identifier;
	private String name;
	private String idTimeConstraint;
	
	public CustomerCategory(int identifier, String name, String idTimeConstraint){
		this.identifier = identifier;
		this.name = name;
		this.idTimeConstraint = idTimeConstraint;
	}
	
	public int getIdentifier(){
		return this.identifier;
	}
	
	public void setIdentifier(int identifier){
		this.identifier = identifier;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getIdTimeConstraint() {
		return idTimeConstraint;
	}

	public void setIdTimeConstraint(String idTimeConstraint) {
		this.idTimeConstraint = idTimeConstraint;
	}
	
	

}
