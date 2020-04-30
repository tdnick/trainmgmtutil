package project;

import java.util.*;

public class TicketDB {
	private HashMap<String, Ticket> tickets;
	
	public TicketDB(){
		this.tickets = new HashMap<String, Ticket>();
	}
	
	public HashMap<String,Ticket> getTickets(){
		return tickets;
	}
	
	public void addTicket(Ticket t){
		tickets.put(t.getSerialNumber(), t);
	}
	
	public void removeTicket(String sn){
		tickets.remove(sn);
	}
	
	public Ticket getTicketBySerialNumber(String sn){
		return tickets.get(sn);
	}
	
	public void displayAllTickets(int opt){
		boolean c = false;
		if (tickets.size() == 0){
			System.out.println("Niciun bilet emis!");
			return;
		}
		for (Ticket a : tickets.values()){
			if (a.getCode() == opt || opt == 0){
				c = true;
				a.displayTicket();
			}
		}
		if (c == false){
			System.out.println("Nu sunt bilete emise pentru trenul cerut!");
			return;
		}
	}
}