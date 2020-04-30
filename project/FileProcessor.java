package project;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.sql.Timestamp;

public class FileProcessor {
	private static FileProcessor instance;
	BufferedReader reader;
	
	private FileProcessor(){}
	
	public static FileProcessor getInstance(){
		if (instance == null){
			instance = new FileProcessor();
		}
		return instance;
	}
	
	public TrainDB readTrains(String path) throws Exception {
		TrainDB fromFile = new TrainDB();
		for (String line : Files.readAllLines(Paths.get(path))) {
			String[] values = line.split(",");
			Train t = new Train(values[0], Integer.parseInt(values[1]), values[2], values[3], values[4], values[5], Integer.parseInt(values[6]));
			Car c;
			for (int i = 0; i < Integer.parseInt(values[7]); i++){
				switch (values[i*2 + 8]) {
					case "s1":
						c = new SeatingCar(1, Boolean.parseBoolean(values[i*2 + 9]));
						break;
						
					case "s2":
						c = new SeatingCar(2, Boolean.parseBoolean(values[i*2 + 9]));
						break;
						
					case "c6":
						c = new Couchette(6);
						break;
						
					case "c4":
						c = new Couchette(4);
						break;
						
					case "d2":
						c = new SleepingCar(Boolean.parseBoolean(values[i*2 + 9]));
						break;
						
					default:
						c = null;
						break;
				}
				t.addCar(c);
			}
			fromFile.addTrain(t);
		}
		return fromFile;
	}
	
	public TicketDB readTickets(String path) throws Exception {
		TicketDB fromFile = new TicketDB();
		for (String line : Files.readAllLines(Paths.get(path))) {
			String[] values = line.split(",");
			Ticket t = new Ticket(values[0], values[1], values[2], values[3], values[4], Integer.parseInt(values[5]), Integer.parseInt(values[6]), Integer.parseInt(values[7]));
			fromFile.addTicket(t);
		}
		return fromFile;
	}
	
	public void writeTrains(String path, TrainDB trains) throws Exception {
		try (PrintWriter writer = new PrintWriter(path)) {
			for (Train t : trains.getTrains()){
				//tip, cod, dep, arr, dist, nr vagoane, carac vagoane
				writer.print(t.getTrainType() + "," + t.getTrainCode() + "," + t.getDep()[0] + "," + t.getDep()[1] + "," + t.getArr()[0] + "," + t.getArr()[1] + "," + t.getDist() + "," + t.getCars().size() + ",");
				for (int i = 0; i < t.getCars().size(); i++){
					writer.print(t.getCars().get(i).getType() + ",");
					switch (t.getCars().get(i).getType()) {
						case "s1":
							writer.print(t.getCars().get(i).hasBar());
							break;
						case "s2":
							writer.print(t.getCars().get(i).hasBar());
							break;
						case "c6":
							writer.print("false");
							break;
						case "c4":
							writer.print("false");
							break;
						case "d2":
							writer.print(t.getCars().get(i).isLuxury());
							break;
						default:
							break;
					}
					if (i < t.getCars().size() - 1) writer.print(",");
				}
				writer.println();
			}
		}
	}
	
	public void writeTickets(String path, TicketDB tickets) throws Exception {
		try (PrintWriter writer = new PrintWriter(path)) {
			for (Map.Entry t : tickets.getTickets().entrySet()){
				//serie, plecare, sosire, vagon, loc, cod
				writer.print(((Ticket)t.getValue()).getSerialNumber() + ",");
				writer.print(((Ticket)t.getValue()).getDep()[0] + "," + ((Ticket)t.getValue()).getDep()[1] + ",");
				writer.print(((Ticket)t.getValue()).getArr()[0] + "," + ((Ticket)t.getValue()).getArr()[1] + ",");
				writer.print(((Ticket)t.getValue()).getSeat()[0] + "," + ((Ticket)t.getValue()).getSeat()[1] + ",");
				writer.println(((Ticket)t.getValue()).getCode());
			}
		}
	}
	
	public void audit(String path, String methodName) throws Exception {
		try (FileWriter writer = new FileWriter(path, true)) {
			PrintWriter out = new PrintWriter(writer);
			out.println(methodName + "," + new Timestamp(System.currentTimeMillis()));
		}
	}
	
}