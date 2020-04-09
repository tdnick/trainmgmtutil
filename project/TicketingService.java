package project;

import java.util.*;

public class TicketingService {
	
	TrainDB trainDatabase;
	TicketDB ticketDatabase;
	
	public TicketingService(){
		trainDatabase = new TrainDB();
		ticketDatabase = new TicketDB();
	}
	
	public void displayMenu(){
		Scanner in = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		String choice = new String();
		
		System.out.println("Train Ticketing and Management Utility");
		System.out.println("--------------------------------------");
		
		while (!choice.equals("q")){
			
			System.out.println("Operatiuni permise:");
			System.out.println("(a) Adauga tren nou");
			System.out.println("(b) Adauga vagoane unui tren existent");
			System.out.println("(c) Afiseaza compunerea unui tren");
			System.out.println("(d) Afiseaza trenuri dupa ruta");
			System.out.println("(e) Vezi toate trenurile in circulatie");
			System.out.println("(f) Anuleaza tren");
			System.out.println("(g) Genereaza bilet nou");
			System.out.println("(h) Afiseaza detalii bilet dupa serie");
			System.out.println("(i) Afiseaza biletele emise...");
			System.out.println("(j) Anuleaza bilet");
			System.out.println();
			System.out.println("(q) Iesire");
			System.out.println("--------------------------------------");
			System.out.print("Optiunea dvs: ");
			choice = in.nextLine();

			switch (choice) {
				case "a":
					//adauga tren
					clearScreen();
					System.out.println("Adaugare tren nou: \n------------------");
					
					System.out.print("Introduceti tipul trenului (R, IR): ");
					String type = in.next().toUpperCase();
					
					System.out.print("Introduceti codul trenului: ");
					int code = in.nextInt();
					in.nextLine();
					
					System.out.print("Statie plecare: ");
					String depSt = in.nextLine();
					System.out.print("La ce ora: ");
					String depTime = in.nextLine();
					
					System.out.print("Statie sosire: ");
					String arrSt = in.nextLine();
					System.out.print("La ce ora: ");
					String arrTime = in.nextLine();
					
					System.out.print("Distanta parcursa: ");
					int dist = in.nextInt();
					in.nextLine();
					
					Train t = new Train(type, code, depSt, depTime, arrSt, arrTime, dist);
					trainDatabase.addTrain(t);
					trainDatabase.sortTrains();
					clearScreen();
					System.out.println("Trenul " + type + code + " a fost adaugat cu succes!\n");
					break;
					
				case "b":
					// Adauga vagoane
					clearScreen();
					Car c;
					System.out.println("Adaugare vagoane: \n-----------------");
					System.out.print("Introduceti codul trenului dorit: ");
					code = in.nextInt();
					in.nextLine();
					Train tr = trainDatabase.getTrainByCode(code);
					if (tr == null){
						clearScreen();
						System.out.println("Trenul nu a fost gasit!\n");
						break;
					}
					
					System.out.print("Cate vagoane adaugati? ");
					int carNumber = in.nextInt();
					in.nextLine();
					for (int i=0; i < carNumber; i++){
						System.out.print("Introduceti tipul vagonului (s1, s2, c6, c4, d2): ");
						type = in.next();
						switch (type) {
							case "s1":
								System.out.print("Are bar? (true/false) ");
								boolean bar = in.nextBoolean();
								c = new SeatingCar(1, bar);
								trainDatabase.addCarToTrain(tr, c);
								System.out.println("Vagon clasa 1 introdus cu succes!\n");
								break;
								
							case "s2":
								System.out.print("Are bar? (true/false) ");
								bar = in.nextBoolean();
								in.nextLine();
								c = new SeatingCar(2, bar);
								trainDatabase.addCarToTrain(tr, c);
								System.out.println("Vagon clasa 2 introdus cu succes!\n");
								break;
								
							case "c6":
								c = new Couchette(6);
								trainDatabase.addCarToTrain(tr, c);
								System.out.println("Vagon cuseta (6 paturi) introdus cu succes!\n");
								break;
								
							case "c4":
								c = new Couchette(4);
								trainDatabase.addCarToTrain(tr, c);
								System.out.println("Vagon cuseta (6 paturi) introdus cu succes!\n");
								break;
								
							case "d2":
								System.out.print("Lux? (true/false) ");
								boolean luxury = in.nextBoolean();
								in.nextLine();
								c = new SleepingCar(luxury);
								trainDatabase.addCarToTrain(tr, c);
								System.out.println("Vagon dormit introdus cu succes!\n");
								break;
								
							default:
								System.out.println("Eroare la introducerea vagonului " + (i+1));
								break;
						}
					}
					clearScreen();
					System.out.println("Vagoane introduse cu succes!\n");
					break;
					
				case "c":
					//compunerea trenului
					clearScreen();
					System.out.println("Afisare compunere tren: \n-----------------------");
					System.out.print("Introduceti codul trenului dorit: ");
					code = in.nextInt();
					in.nextLine();
					System.out.println();
					trainDatabase.displayTrainComposition(code);
					System.out.println();
					break;
					
				case "d":
					//trenuri pe o anumita ruta
					clearScreen();
					System.out.println("Afisare trenuri pe anumita ruta: \n-------------------------------");
					System.out.print("Statie plecare: ");
					depSt = in.nextLine().toUpperCase();
					System.out.print("Statie sosire: ");
					arrSt = in.nextLine().toUpperCase();
					System.out.println();
					boolean ch = trainDatabase.queryTrainsByRoute(depSt, arrSt);
					if (!ch){
						clearScreen();
						System.out.println("Nu a fost gasit niciun tren pe ruta dorita!");
						break;
					}
					System.out.println();
					break;
				case "e":
					//toate trenurile
					clearScreen();
					System.out.println("Afisare toate trenurile din baza de date: \n------------------------------");
					ch = trainDatabase.queryTrainsByRoute("*", "*");
					if (!ch){
						clearScreen();
						System.out.println("Nu exista niciun tren in baza de date!");
						break;
					}
					System.out.println();
					break;
				case "f":
					//sterge tren din baza de date
					clearScreen();
					System.out.println("Anulare tren: \n---------------");
					System.out.print("Introduceti codul trenului dorit: ");
					code = in.nextInt();
					in.nextLine();
					Train r = trainDatabase.getTrainByCode(code);
					if (r == null){
						clearScreen();
						System.out.println("Trenul nu exista!");
						break;
					}
					trainDatabase.removeTrain(r);
					clearScreen();
					System.out.println("Tren eliminat cu succes!");
					break;
				case "g":
					// genereaza bilet
					clearScreen();
					System.out.println("Pasul 1: Alege ruta \n------------------------");
					System.out.print("Statie plecare: ");
					depSt = in.nextLine();
					System.out.print("Statie sosire: ");
					arrSt = in.nextLine();
					System.out.println("\nRezultatele cererii dvs:\n-------------------");
					ch = trainDatabase.queryTrainsByRoute(depSt, arrSt);
					if (!ch){
						clearScreen();
						System.out.println("Nu a fost gasit niciun tren pe ruta dorita!");
						break;
					}
					System.out.print("Introduceti codul trenului dorit: ");
					int choiceOfTrain = in.nextInt();
					in.nextLine();
					clearScreen();
					ch = trainDatabase.displayTrainComposition(choiceOfTrain);
					if (!ch){
						System.out.println("Trenul introdus nu exista! ");
						break;
					}
					System.out.print("Cauta loc la vagon: ");
					int carChoice = in.nextInt();
					in.nextLine();
					
					int seatNumber = trainDatabase.getAvailableSeat(choiceOfTrain, carChoice);
					
					if (seatNumber == -1){
						clearScreen();
						System.out.println("Nu mai sunt locuri disponibile la acest tip de vagon!");
						break;
					}
					if (seatNumber == -2){
						clearScreen();
						System.out.println("Vagonul specificat nu exista!");
						break;
					}
					String serialNumber = generateRandomString().toUpperCase();
					String[] dep = trainDatabase.getTrainByCode(choiceOfTrain).getDep();
					String[] arr = trainDatabase.getTrainByCode(choiceOfTrain).getArr();
					String trainType = trainDatabase.getTrainByCode(choiceOfTrain).getTrainType();
					System.out.println("\nConfirmare bilet seria " + serialNumber + ": ");
					System.out.print("Tren " + trainType + choiceOfTrain + " | ");
					System.out.print(dep[0] + " (" + dep[1] + ") -> ");
					System.out.println(arr[0] + " (" + arr[1] + ")");
					System.out.println("Vagon " + carChoice + ", Loc " + (seatNumber + 1));
					System.out.print("Rezervati biletul? (y) ");
					String confirm = in.nextLine();
					if (!confirm.equals("y")){
						System.out.println("Operatiune anulata.");
						break;
					}
					clearScreen();
					Ticket ticket = new Ticket(serialNumber, dep[0], dep[1], arr[0], arr[1], carChoice - 1, seatNumber, choiceOfTrain);
					
					int[] seatInfo = new int[]{carChoice - 1, seatNumber};
					
					trainDatabase.reserveSeat(choiceOfTrain, seatInfo, serialNumber);
					ticketDatabase.addTicket(ticket);
					System.out.println("Bilet seria " + serialNumber + " generat cu succes!\n");
					break;
				case "h":
					System.out.print("Introduceti seria: ");
					String opt = in.nextLine().toUpperCase();
					Ticket ret = ticketDatabase.getTicketBySerialNumber(opt);
					clearScreen();
					if (ret == null){
						System.out.println("Nu exista bilet cu aceasta serie!\n");
						break;
					}
					ret.displayTicket();
					System.out.println();
					break;
				case "i":
					clearScreen();
					System.out.println("Afisare bilete emise: \n---------------");
					System.out.print("Introduceti codul trenului dorit (sau 0 pentru toate trenurile): ");
					int al = in.nextInt();
					in.nextLine();
					clearScreen();
					ticketDatabase.displayAllTickets(al); 
					System.out.println();
					break;
				case "j":
					clearScreen();
					System.out.println("Anulare bilet: \n---------------");
					System.out.print("Introduceti seria biletului dorit: ");
					String sn = in.nextLine().toUpperCase();
					Ticket tt = ticketDatabase.getTicketBySerialNumber(sn);
					if (tt == null){
						clearScreen();
						System.out.println("Nu exista bilet cu aceasta serie!\n");
						break;
					}
					Train del = trainDatabase.getTrainByCode(tt.getCode());
					int seat[] = tt.getSeat();
					del.getCars().get(seat[0]).getSeats()[seat[1]].unassignSeat(); //sterge rezervare loc
					ticketDatabase.removeTicket(sn);
					clearScreen();
					System.out.println("Bilet anulat cu succes!\n")
					break;
				case "q":
					System.out.println("Programul se va inchide.");
					break;		
				default:
					clearScreen();
					System.out.println("Optiune invalida.\n");
			}

		}
		
	}
	
	public static final void clearScreen(){
		for(int i = 0; i < 80*300; i++)
			System.out.print("\b");
	}
	
	public static String generateRandomString() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 8;
		Random random = new Random();
		
		String generatedString = random.ints(leftLimit, rightLimit + 1)
			.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
			.limit(targetStringLength)
			.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			.toString();
		
		return generatedString;
	}
}