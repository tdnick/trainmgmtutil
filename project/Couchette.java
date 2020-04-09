package project;

public class Couchette extends Car{
	private int beds;
	
	public Couchette(int beds){
		super("c" + beds, 2, beds * 7);
		this.beds = beds;
	}
	
	public void displayInfo(){
		super.displayInfo();
		System.out.println("Paturi/compartiment: " + beds);
	}
	
	public boolean isLuxury(){
		return false;
	}
	public boolean hasBar(){
		return false;
	}
}