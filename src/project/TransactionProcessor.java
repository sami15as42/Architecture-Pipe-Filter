package project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionProcessor extends Filtre {
	private Map<String, Client> clients = new HashMap<String, Client>();
	private Map<String, StockItem> stockItems = new HashMap<String, StockItem>();
	private List<RentedItem> rentedItems = new ArrayList<RentedItem>();
	private int idClients = 0;
	private int idItems = 0;

	public TransactionProcessor(Pipe _dataINPipe, Pipe _dataOUTPipe) {
		super();
		this._dataINPipe = _dataINPipe;
		this._dataOUTPipe = _dataOUTPipe;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		execute();
	}

	@Override
	synchronized void execute() {
		// TODO Auto-generated method stub
		String transaction;
		do {
			transaction = _dataINPipe.dataOUT();
			StringBuilder sb = new StringBuilder(transaction);
			String[] params;
			sb.deleteCharAt(transaction.length() - 1);
			sb.deleteCharAt(0);
			sb.deleteCharAt(sb.toString().length() - 1);
			sb.deleteCharAt(sb.toString().length() - 1);
			sb.deleteCharAt(0);

			params = sb.toString().split("\",\"");

			switch (params[0]) {
			case "1":
				String films_s = "";
				String acteur = params[1];

				List<Film> films = NdByActor(acteur);
				for (Film film : films) {
					films_s += "- " + film.getItemID() + " : " + film.getTitle() + ", rental price: "
							+ Double.toString(film.getRentalPrice()) + " DA\n";
				}
				if (films.isEmpty()) {
					_dataOUTPipe.dataIN("No availabe films for this actor");
				} else {
					_dataOUTPipe.dataIN(films_s);
				}
				break;
			case "2":

				String title = params[1];

				if (FindByTitle(title))
					_dataOUTPipe.dataIN("Item available for rental");
				else
					_dataOUTPipe.dataIN("Item not available for rental");
				break;
			case "3":
				String name = params[1];
				List<RentedItem> rented_films = RentedFilms(name);

				films_s = "";
				for (RentedItem film : rented_films) {
					films_s += "- " + film.getItem().getItemID() + " : " + film.getItem().getTitle() + ", Acteur : "
							+ ((Film) film.getItem()).getActeur() + ", Date : " + film.getDueDate().toString() + "\n";
				}

				if (rented_films.isEmpty()) {
					_dataOUTPipe.dataIN("No films rented by this client.");
				} else {
					_dataOUTPipe.dataIN(films_s);
				}

				break;
			case "4":
				name = params[1];
				double solde = Solde(name);
				if (solde == -1.0) {
					_dataOUTPipe.dataIN("The entred name isn't a client.");
				} else {
					_dataOUTPipe.dataIN(Double.toString(solde) + " DA");
				}

				break;
			case "5":
				String overdueItems_s = "";
				ArrayList<RentedItem> overdueItems = OverdueItems();
				for (RentedItem item : overdueItems) {
					overdueItems_s += "- " + item.getItem().getItemID() + " : " + item.getItem().getTitle()
							+ ", Client : " + item.getCustomer().getName() + ", Due Date : "
							+ item.getDueDate().toString() + "\n";
				}
				if (overdueItems.isEmpty()) {
					_dataOUTPipe.dataIN("No overdued items.");
				} else {
					_dataOUTPipe.dataIN(overdueItems_s);
				}

				break;
			case "6":
				name = params[1];
				title = params[2];
				String dueDate = params[3];

				if (CheckOut(title, name, dueDate))
					_dataOUTPipe.dataIN("Item rented successfully.");
				else
					_dataOUTPipe.dataIN("Operation failed.");
				break;
			case "7":
				name = params[1];
				title = params[2];

				if (CheckIn(title, name))
					_dataOUTPipe.dataIN("Item returned successfully.");
				else
					_dataOUTPipe.dataIN("Operation failed.");
				break;
			case "8":
				String rentalPrice_s = params[1];
				try {
					double rentalPrice = Float.parseFloat(rentalPrice_s);
					title = params[2];
					String game_s = params[3];
					Boolean isGame = Boolean.parseBoolean(game_s);
					String plateforme = params[4];
					acteur = params[5];
					if (AddStockItem(rentalPrice, title, isGame, plateforme, acteur))
						_dataOUTPipe.dataIN("Item added successfully.");
					else
						_dataOUTPipe.dataIN("Operation failed.");
					break;
				} catch (NumberFormatException nfe) {
					_dataOUTPipe.dataIN("Operation failed. Please entre a valide balance.");
					break;
				}

			case "9":
				name = params[1];
				String accountBalance_s = params[2];
				try {
					double accountBalance = Float.parseFloat(accountBalance_s);

					if (AddCustomer(name, accountBalance))
						_dataOUTPipe.dataIN("Client added successfully.");
					else
						_dataOUTPipe.dataIN("Operation failed.");
					break;
				} catch (NumberFormatException nfe) {
					_dataOUTPipe.dataIN("Operation failed. Please entre a valide balance.");
					break;
				}

			case "10":
				name = params[1];
				String penality_s = params[2];
				try {
					double penality = Float.parseFloat(penality_s);

					if (AddPenality(name, penality))
						_dataOUTPipe.dataIN("Client balnace debited.");
					else
						_dataOUTPipe.dataIN("Not enough balance or client account doesn't exist.");
					break;
				} catch (NumberFormatException nfe) {
					_dataOUTPipe.dataIN("Operation failed. Please entre a valide balance.");
					break;
				}

			}
		} while (transaction != "0");
	}

	public boolean AddPenality(String name, double penality) {
		Client client = this.clients.get(name);
		if (client != null) {
			if (client.getAccountBalance() >= penality) {
				client.setAccountBalance(client.getAccountBalance() - penality);
				return true;
			}
		}
		return false;
	}

	public double Solde(String name) {
		Client client = this.clients.get(name);
		if (client != null)
			return client.getAccountBalance();
		return -1;
	}

	public List<Film> NdByActor(String acteur) {
		ArrayList<Film> films = new ArrayList<Film>();
		for (String title : this.stockItems.keySet()) {
			StockItem item = this.stockItems.get(title);
			if ((item instanceof Film) && (((Film) item).getActeur().equals(acteur))) {
				films.add((Film) item);
			}
		}
		return films;
	}

	public boolean FindByTitle(String title) {
		if (this.stockItems.containsKey(title)) {
			for (RentedItem item : this.rentedItems) {
				if (item.getItem().getTitle().equals(title))
					return false;
			}
			return true;
		} else
			return false;
	}

	public ArrayList<RentedItem> OverdueItems() {

		Client client = new Client(150, "Sami", 1);
		Film itemm = new Film(150, "title", 1, "acteur");
		try {
			Date dd = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998");
			this.rentedItems.add(new RentedItem(client, itemm, dd));
		} catch (Exception e) {
		}

		Date currentDate = new Date();
		ArrayList<RentedItem> liste = new ArrayList<RentedItem>();
		for (RentedItem element : this.rentedItems) {
			if (element.getDueDate().before(currentDate)) {
				liste.add(element);
			}
		}
		return liste;
	}

	public ArrayList<RentedItem> RentedFilms(String name) {
		/*
		 * Client client = new Client(150, "Sami", 1); Film itemm = new Film(150,
		 * "title", 1, "acteur"); try { Date dd = new
		 * SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998"); this.rentedItems.add(new
		 * RentedItem(client, itemm, dd));} catch(Exception e) {}
		 */

		ArrayList<RentedItem> liste = new ArrayList<RentedItem>();
		for (RentedItem element : this.rentedItems) {
			if ((element.getItem() instanceof Film) && (element.getCustomer().getName().equals(name)))
				liste.add(element);
		}
		return liste;
	}

	public boolean CheckOut(String articleTitle, String clientName, String dueDate) {
		for (RentedItem item : this.rentedItems) {
			if (item.getItem().getTitle().equals(articleTitle))
				return false;
		}
		StockItem item = this.stockItems.get(articleTitle);
		Client client = this.clients.get(clientName);
		if (item != null && client != null && item.getRentalPrice() <= client.getAccountBalance()) {
			client.setAccountBalance(client.getAccountBalance() - item.getRentalPrice());
			try {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dueDate);
				this.rentedItems.add(new RentedItem(client, item, date));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	public boolean CheckIn(String articleTitle, String clientName) {
		for (RentedItem element : this.rentedItems) {
			if ((element.getCustomer().getName().equals(clientName))
					&& (element.getItem().getTitle().equals(articleTitle))) {
				this.rentedItems.remove(element);
				return true;
			}
		}
		return false;
	}

	public boolean AddCustomer(String name, double accountBalance) {
		if (this.clients.containsKey(name) == false) {
			this.clients.put(name, new Client(accountBalance, name, this.idClients));
			this.idClients += 1;
			return true;
		}
		return false;
	}

	public boolean AddStockItem(double rentalPrice, String title, boolean isGame, String plateforme, String acteur) {

		if (this.stockItems.containsKey(title) == false) {
			if (isGame) {
				this.stockItems.put(title, new Jeux(rentalPrice, title, this.idItems, plateforme));
			} else {
				this.stockItems.put(title, new Film(rentalPrice, title, this.idItems, acteur));
			}
			this.idItems += 1;
			return true;
		} else
			return false;
	}
}
