package project;

public class Ticket {
	private String serialNumber;
	private String[] dep, arr;
	private int carNumber, seatNumber, trainCode;
	
	public Ticket(String sn, String depSt, String depTime, String arrSt, String arrTime, int car, int seat, int code){
		this.serialNumber = sn;
		this.dep = new String[2];
		this.arr = new String[2];
		this.dep[0] = depSt;
		this.dep[1] = depTime;
		this.arr[0] = arrSt;
		this.arr[1] = arrTime;
		this.carNumber = car;
		this.seatNumber = seat;
		this.trainCode = code;
	}
	
	public String getSerialNumber(){
		return serialNumber;
	}
	
	public int getCode(){
		return trainCode;
	}
	
	public int[] getSeat(){
		return new int[]{carNumber, seatNumber};
	}
	
	public void displayTicket(){
		System.out.println("Bilet seria " + this.serialNumber);
		System.out.println("Plecare: " + this.dep[0] + " (" + this.dep[1] + ")");
		System.out.println("Sosire: " + this.arr[0] + " (" + this.arr[1] + ")");
		System.out.println("Tren " + this.trainCode);
		System.out.println("Vagon " + (this.carNumber + 1) + ", Loc " + (this.seatNumber + 1));
		System.out.println("----------------------");
	}
}