package project;

import java.util.*;

public class Train {
	private String trainType;
	private int trainCode, dist;
	private ArrayList<Car> trainCars;
	private String[] dep, arr;
	
	public Train(String trainType, int trainCode, String depSt, String depTime, String arrSt, String arrTime, int dist){
		this.trainType = trainType;
		this.trainCode = trainCode;
		this.dep = new String[2];
		this.arr = new String[2];
		this.dep[0] = depSt;
		this.dep[1] = depTime;
		this.arr[0] = arrSt;
		this.arr[1] = arrTime;
		this.dist = dist;
		this.trainCars = new ArrayList<Car>();
	}
	
	public String getTrainType(){
		return trainType;
	}
	
	public int getDist(){
		return dist;
	}
	
	public int getTrainCode(){
		return trainCode;
	}
	
	public String[] getDep(){
		return dep;
	}
	
	public String[] getArr(){
		return arr;
	}
	
	public void addCar(Car c){
		trainCars.add(c);
	}
	
	public ArrayList<Car> getCars(){
		return trainCars;
	}
	
	private boolean hasCars(){
		return trainCars.size() != 0;
	}
	
	public void showCars(){
		if (!this.hasCars()) System.out.println("Trenul nu are in compunere niciun vagon!");
		else{
			Iterator<Car> carIterator = trainCars.iterator();
			int cnt = 1;
			while(carIterator.hasNext()){
				Car c = carIterator.next();
				System.out.println("Vagon " + cnt);
				System.out.println("------------");
				c.displayInfo();
				cnt++;
			}
		}
	}
	
	public String getServicesAsString(){
		String ret = new String();
		if (!this.hasCars()) ret = "Niciun serviciu disponibil";
		else{
			Iterator<Car> carIterator = trainCars.iterator();
			while(carIterator.hasNext()){
				Car c = carIterator.next();
				String feature = c.getType();
				switch (feature) {
					case "s1":
						ret += "Vagon clasa 1";
						if (c.hasBar()) ret += " cu bar";
						break;
					case "s2":
						ret += "Vagon clasa 2";
						if (c.hasBar()) ret += " cu bar";
						break;
					case "c6":
						ret += "Vagon cuseta (6 paturi)";
						break;
					case "c4":
						ret += "Vagon cuseta (4 paturi)";
						break;
					case "d2":
						if (c.isLuxury()) ret += "Vagon dormit lux (2 paturi)";
						else ret += "Vagon dormit (2 paturi)";
					default:
						break;
				}
				if (carIterator.hasNext()) ret += ", ";
				else ret += "\n";
			}
		}
		return ret;
	}
	
}