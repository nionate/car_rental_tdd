package cl.ubb.agil.model;

public class CustomerCategory {
	
	private int identifier;
	private String name;
	
	public CustomerCategory(int identifier, String name){
		this.identifier = identifier;
		this.name = name;
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

}
