package project;

public class SeatingCar extends Car{
	private boolean bar;
	
	public SeatingCar(int carClass, boolean bar){
		super("s" + carClass, carClass, carClass == 2 ? 80 : 60);
		this.bar = bar;
	}
	
	public void displayInfo(){
		super.displayInfo();
		System.out.println("Bar: " + (bar == true ? "da" : "nu"));
	}
	
	public boolean hasBar(){
		return bar;
	}
	
	public boolean isLuxury(){
		return false;
	};
}