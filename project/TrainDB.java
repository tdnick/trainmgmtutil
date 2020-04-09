package project;

import java.util.*;

public class TrainDB {
	private ArrayList<Train> trains;
	
	public TrainDB(){
		this.trains = new ArrayList<Train>();
	}
	
	public void addTrain(Train t){
		this.trains.add(t);
	}
	
	public void sortTrains(){
		Collections.sort(trains, Comparator.comparing(Train::getTrainCode));
	}
	
	public void removeTrain(Train t){
		if (t == null){
			System.out.println("Trenul nu exista!");
			return;
		}
		trains.remove(t);
	}
	
	public Train getTrainByCode(int code){
		for (Train t : this.trains)
			if (t.getTrainCode() == code)
				return t;

		return null;
	}
	
	public void displayTrainInfo(int code){
		Train t = getTrainByCode(code);
		if (t == null){
			System.out.println("Trenul nu exista!");
			return;
		}
		System.out.print(t.getTrainType() + t.getTrainCode() + ": ");
		System.out.print(t.getDep()[0] + " (" + t.getDep()[1] + ") -> ");
		System.out.println(t.getArr()[0] + " (" + t.getArr()[1] + ")");
		
	}
	
	public boolean displayTrainComposition(int code){
		//cauta tren dupa cod si apoi afiseaza
		Train t = getTrainByCode(code);
		boolean status = true;
		if (t == null){
			System.out.println("Trenul nu exista!");
			status = false;
		}
		
		System.out.println("Afisarea compunerii trenului " + t.getTrainType() + code + ": ");
		for (int i = 0; i < t.getCars().size(); i++)
		{
			System.out.print("Vagon " + (i+1) + ": ");
			switch (t.getCars().get(i).getType()) {
				case "s1":
					System.out.print("(s1) Vagon clasa 1");
					if (t.getCars().get(i).hasBar()) System.out.println(" cu bar");
					else System.out.println();
					break;
				case "s2":
					System.out.print("(s2) Vagon clasa 2");
					if (t.getCars().get(i).hasBar()) System.out.println(" cu bar");
					else System.out.println();
					break;
				case "c6":
					System.out.println("(c6) Vagon cuseta (6 paturi)");
					break;
				case "c4":
					System.out.println("(c4) Vagon cuseta (4 paturi)");
					break;
				case "d2":
					System.out.print("(d2) Vagon dormit");
					if (t.getCars().get(i).isLuxury()) System.out.println(" lux");
					else System.out.println();
					break;
				default:
					break;
			}
		}
		return status;
	}
	
	public void addCarToTrain(Train t, Car c){
		t.addCar(c);
	}
	
	public boolean queryTrainsByRoute(String dep, String arr){
		boolean c = false;
		for (Train t : this.trains)
			if( ((t.getDep()[0].toUpperCase()).equals(dep.toUpperCase()) && (t.getArr()[0].toUpperCase()).equals(arr.toUpperCase())) || (dep == "*" && arr == "*")){
				displayTrainInfo(t.getTrainCode());
				c = true;
			}
		return c;
	}
	
	public int getAvailableSeat(int code, int number){
		Train t = getTrainByCode(code);
		int nr = -1; //returnarea lui -1 inseamna ca nu exista loc liber
		if (t == null){
			System.out.println("Trenul nu exista!");
			return -3; //trenul nu exista
		}
		if (number > t.getCars().size()){
			System.out.println("Nu exista vagonul respectiv!");
			return -2; //vagonul nu exista
		}
		Car c = t.getCars().get(number - 1);
		for (int i = 0; i < c.getNumberOfSeats(); i++){
			if (c.getSeats()[i].isAvailable()){
				nr = i;
				break;
			}
		}
		return nr;
	}
		
	public void reserveSeat(int code, int[] seatInfo, String serialNumber){
		Train t = getTrainByCode(code);
		t.getCars().get(seatInfo[0]).getSeats()[seatInfo[1]].assignSeat(serialNumber);
	}
}