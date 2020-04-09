package project;

//// Program de management al trenurilor si biletelor emise

// Presupunem emiterea biletelor in aceeasi zi
// Presupunem trenuri fara opriri intermediare
// Calcul pret bilet inca neimplementat

class Main {
	public static void main(String[] args) {
		TicketingService app = new TicketingService();
		app.displayMenu();
	}
}