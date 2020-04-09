package project;

import java.util.*;

public abstract class Car {
	private String typeCode;
	private int carClass;
	private Seat[] seats;
	private int numberOfSeats;
	
	public Car(String typeCode, int carClass, int numberOfSeats){
		this.typeCode = typeCode;
		this.carClass = carClass;
		this.numberOfSeats = numberOfSeats;
		this.seats = new Seat[numberOfSeats];
		for (int i = 0; i<numberOfSeats; i++){
			this.seats[i] = new Seat();
		}
	}
	
	public void displayInfo(){
		System.out.println("Tip vagon: " + typeCode);
		System.out.println("Clasa: " + carClass);
		System.out.println("Numar total locuri: " + numberOfSeats);
	}
	
	public String getType(){
		return typeCode;
	}
	
	public int getClassOfCar(){
		return carClass;
	}
	
	public Seat[] getSeats(){
		return seats;
	}
	
	public int getNumberOfSeats(){
		return numberOfSeats;
	}
	
	abstract boolean hasBar();
	abstract boolean isLuxury();
}