package project;

public class Seat {
	private boolean available;
	private String assignedTo;
	
	public Seat(){
		available = true;
		assignedTo = "none";
	}
	
	public void assignSeat(String ticketID){
		assignedTo = ticketID;
		available = false;
	}
	
	public void unassignSeat(){
		assignedTo = "none";
		available = true;
	}
	
	public boolean isAvailable(){
		return available;
	}
	
}