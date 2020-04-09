package project;

public class SleepingCar extends Car{
	private boolean luxury;
	
	public SleepingCar(boolean luxury){
		super("d2", 1, 12);
		this.luxury = luxury;
	}
	
	public void displayInfo(){
		super.displayInfo();
	}
	
	public boolean isLuxury(){
		return luxury;
	}
	
	public boolean hasBar(){
		return false;
	}
}